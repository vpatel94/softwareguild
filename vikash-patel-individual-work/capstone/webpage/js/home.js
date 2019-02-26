$(document).ready(function(){

    loadDoctors();

    $('#new-patient-button').click(function(event) {

        // check for errors and display any that we have
        // pass the input associated with the add form to the validation function
        var haveValidationErrors = checkAndDisplayValidationErrors($('#create-patient-form').find('input'));

        // if we have errors, bail out by returning false
        if (haveValidationErrors) {
            return false;
        }

        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/api/luxMD/myCare/newPatient',
            data: JSON.stringify({
                firstName: $('#new-fname').val(),
                lastName: $('#new-lname').val(),
                phoneNumber: $('#new-phoneNumber').val(),
                streetAddress: $('#new-streetAddress').val(),
                city: $('#new-city').val(),
                state: $('#new-state').val(),
                zipCode: $('#new-zipCode').val(),
                internalNote: null
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function(data, status) {
                alert("PLEASE RETAIN THIS NUMBER, YOU WILL NEED IT TO LOG IN \n-------\nPatient ID: " + data + "\n-------");
                hideRegisterForm();
            },
            error: function() {
                $('#errorMessages')
                   .append($('<li>')
                   .attr({class: 'list-group-item list-group-item-danger'})
                   .text('Error calling web service.  Please try again later.'));
            }
        });

    })

});

function loadDoctors() {
    //Primary
    $.ajax ({
        type: 'GET',
        url: 'http://localhost:8080/api/luxMD/ourServices/ourDoctors/1',
        success: function (data, status) {
            $.each(data, function (index, doctor) {
                var doctorId = doctor.doctorId;
                var doctorName = doctor.firstName + " " + doctor.lastName;

                var doc = '<p>' + doctorName + '</p>';
                $('#doctorList').append(doc);
            });
        },
        error: function() {
            $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service.  Please try again later.'));
        }
    });
    //Nurse
    $.ajax ({
        type: 'GET',
        url: 'http://localhost:8080/api/luxMD/ourServices/ourDoctors/2',
        success: function (data, status) {
            $.each(data, function (index, doctor) {
                var doctorId = doctor.doctorId;
                var doctorName = doctor.firstName + " " + doctor.lastName;

                var doc = '<p>' + doctorName + '</p>';
                $('#nurseList').append(doc);
            });
        },
        error: function() {
            $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service.  Please try again later.'));
        }
    });
    //Physical Therapists
    $.ajax ({
        type: 'GET',
        url: 'http://localhost:8080/api/luxMD/ourServices/ourDoctors/3',
        success: function (data, status) {
            $.each(data, function (index, doctor) {
                var doctorId = doctor.doctorId;
                var doctorName = doctor.firstName + " " + doctor.lastName;

                var doc = '<p>' + doctorName + '</p>';
                $('#ptList').append(doc);
            });
        },
        error: function() {
            $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service.  Please try again later.'));
        }
    });
    //Nutritionists
    $.ajax ({
        type: 'GET',
        url: 'http://localhost:8080/api/luxMD/ourServices/ourDoctors/4',
        success: function (data, status) {
            $.each(data, function (index, doctor) {
                var doctorId = doctor.doctorId;
                var doctorName = doctor.firstName + " " + doctor.lastName;

                var doc = '<p>' + doctorName + '</p>';
                $('#nutritionistList').append(doc);
            });
        },
        error: function() {
            $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service.  Please try again later.'));
        }
    });
}

function showRegisterForm() {
    $('#errorMessages').empty();
    $('#content').hide();
    $('#registerDiv').show();

}

function hideRegisterForm() {
    $('#errorMessages').empty();
    $('#new-fname').val("");
    $('#new-lname').val("");
    $('#new-phoneNumber').val("");
    $('#new-streetAddress').val("");
    $('#new-city').val("");
    $('#new-state').val("");
    $('#new-zipCode').val("");
    $('#registerDiv').hide();
    $('#content').show();
}

function redirect(){
    var userId = document.getElementById("user-id");
    var userType = $('input[name=userRadio]:checked', '#sign-in-form').val();
    var patientPage = 'myCare.html?' + userId.value;
    var doctorPage = 'ourPC.html?' + userId.value;

    if(userType == 'patient'){
        window.location = patientPage;
    }
    else if(userType == 'doctor'){
        window.location = doctorPage;
    }
    else{
        alert('Please select your user type');
    }

    return false; // prevent further bubbling of event
}

// processes validation errors for the given input.  returns true if there
// are validation errors, false otherwise
function checkAndDisplayValidationErrors(input) {
    // clear displayed error message if there are any
    $('#errorMessages').empty();
    // check for HTML5 validation errors and process/display appropriately
    // a place to hold error messages
    var errorMessages = [];

    // loop through each input and check for validation errors
    input.each(function() {
        // Use the HTML5 validation API to find the validation errors
        if(!this.validity.valid)
        {
            var errorField = $('label[for='+this.id+']').text();
            errorMessages.push(errorField + ' ' + this.validationMessage);
        }
    });

    // put any error messages in the errorMessages div
    if (errorMessages.length > 0){
        $.each(errorMessages,function(index,message){
            $('#errorMessages').append($('<li>').attr({class: 'list-group-item list-group-item-danger'}).text(message));
        });
        // return true, indicating that there were errors
        return true;
    } else {
        // return false, indicating that there were no errors
        return false;
    }
}
