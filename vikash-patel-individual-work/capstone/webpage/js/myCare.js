var locate = window.location;
$('#global_id').val(locate);
var url = $('#global_id').val();
var patId_global = url.substr(url.indexOf("?") + 1);
$(document).ready(function () {

    loadPatient();
    loadAppointments();

    $("#datepicker, #edit-datepicker").datepicker({dateFormat: "yy-mm-dd"});
    $("#timepicker, #edit-timepicker").timepicker({
        timeFormat: 'HH:mm:ss',
        interval: 30,
        minTime: '10',
        maxTime: '18:00',
        defaultTime: '9',
        startTime: '10:00',
        dynamic: false,
        dropdown: true,
        scrollbar: true
    });

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/luxMD/ourServices',
        success: function (data, status) {
            $.each(data, function (index, service) {
                var servId = service.serviceId;
                var careType = service.careType;

                var option = '<option value="' + servId + '">' + careType + '</option>';
                $('#care-options').append(option);
            });
        },
        error: function() {
            $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service.  Please try again later.'));
        }
    });

    $("#care-options").change(function() {

        var $option = $(this).find('option:selected');
        loadDoctors($option.val());
    });

    // Add Button onclick handler
    $('#create-button').click(function (event) {

        // check for errors and display any that we have
        // pass the input associated with the add form to the validation function
        var haveValidationErrors = checkAndDisplayValidationErrors($('#appointment-form').find('input'));

        // if we have errors, bail out by returning false
        if (haveValidationErrors) {
            return false;
        }

        // if we made it here, there are no errors so make the ajax call
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/api/luxMD/myCare/newAppointment',
            data: JSON.stringify({
                patientId: patId_global,
                doctorId: $('#doctor-options').find('option:selected').val(),
                date: $('#datepicker').val(),
                time: $('#timepicker').val(),
                notes: $('#appointment-notes').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function(data, status) {
                // clear errorMessages
                $('#errorMessages').empty();
               // Clear the form and reload the table
                $('#datepicker').val('');
                $('#timepicker').val('');
                $('#appointment-notes').val('');
                loadAppointments();
            },
            error: function() {
                $('#errorMessages')
                   .append($('<li>')
                   .attr({class: 'list-group-item list-group-item-danger'})
                   .text('Error calling web service.  Please try again later.'));
            }
        });
    });

    // Update Button onclick handler
    $('#edit-appt-button').click(function (event) {

        // check for errors and display any that we have
        // pass the input associated with the edit form to the validation function
        var haveValidationErrors = checkAndDisplayValidationErrors($('#edit-appt-form').find('input'));

        // if we have errors, bail out by returning false
        if (haveValidationErrors) {
            return false;
        }

        // if we get to here, there were no errors, so make the Ajax call
        $.ajax({
           type: 'POST',
           url: 'http://localhost:8080/api/luxMD/myCare/editAppointment/' + $('#edit-apptId').val(),
           data: JSON.stringify({
             patientId: null,
             doctorId: null,
             date: $('#edit-datepicker').val(),
             time: $('#edit-timepicker').val(),
             notes: $('#edit-notes').val()
           }),
           headers: {
             'Accept': 'application/json',
             'Content-Type': 'application/json'
           },
           'dataType': 'json',
            success: function(data, status) {
                // clear errorMessages
                $('#errorMessages').empty();
                hideEditApptForm();
                loadAppointments();
           },
           error: function(error) {
             $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service.  Please try again later.'));
           }
       })
    });

    $('#edit-profile-button').click(function (event) {

        $('#intro').empty();
        $('#editProf').empty();
        // check for errors and display any that we have
        // pass the input associated with the edit form to the validation function
        var haveValidationErrors = checkAndDisplayValidationErrors($('#edit-profile-form').find('input'));

        // if we have errors, bail out by returning false
        if (haveValidationErrors) {
            return false;
        }

        // if we get to here, there were no errors, so make the Ajax call
        $.ajax({
           type: 'POST',
           url: 'http://localhost:8080/api/luxMD/myCare/editProfile/' + patId_global,
           data: JSON.stringify({
             patientId: patId_global,
             firstName: $('#edit-profile-fname').val(),
             lastName: $('#edit-profile-lname').val(),
             phoneNumber: $('#edit-profile-phoneNumber').val(),
             streetAddress: $('#edit-profile-streetAddress').val(),
             city: $('#edit-profile-city').val(),
             state: $('#edit-profile-state').val(),
             zipCode: $('#edit-profile-zipCode').val()
           }),
           headers: {
             'Accept': 'application/json',
             'Content-Type': 'application/json'
           },
           'dataType': 'json',
            success: function(data, status) {
                // clear errorMessages
                $('#errorMessages').empty();
                hideEditProfile();
                loadAppointments();
                loadPatient();
           },
           error: function(error) {
             $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service.  Please try again later.'));
           }
       });
    });
});

function loadDoctors(serviceId) {

    $('#doctor-options').empty();
    $.ajax ({
        type: 'GET',
        url: 'http://localhost:8080/api/luxMD/ourServices/ourDoctors/' + serviceId,
        success: function (data, status) {
            $.each(data, function (index, doctor) {
                var doctorId = doctor.doctorId;
                var doctorName = doctor.firstName + " " + doctor.lastName;

                var option = '<option value="' + doctorId + '">' + doctorName + '</option>';
                $('#doctor-options').append(option);
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

function loadPatient() {

    $.ajax ({
        type: 'GET',
        url: 'http://localhost:8080/api/luxMD/myCare/Patient/' + patId_global,
        success: function (patient, status) {
            var patId = patient.patientId;
            var firstName = patient.firstName;
            var lastName = patient.lastName;
            $("#intro").append("Welcome " + firstName + " " + lastName + "! (ID: " + patId_global + ")");
            $("#editProf").append('<a onclick="showEditProfile(' + patId + ')">Edit Patient Profile</a>')
        },
        error: function() {
            $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service.  Please try again later.'));
        }
    });
}

function loadAppointments() {
    // we need to clear the previous content so we don't append to it
    clearAppointmentsTable();

    // grab the the tbody element that will hold the rows of contact information
    var contentRows = $('#contentRows');

    $.ajax ({
        type: 'GET',
        url: 'http://localhost:8080/api/luxMD/myCare/myAppointments/' + patId_global,
        success: function (data, status) {
            data = data.sort(function(a, b) {
                var first = new Date(a.dateTime);
                var second = new Date(b.dateTime);
                return ( first > second ) ? 1 : ( first < second ) ? -1 : 0;
            });
            $.each(data, function (index, appt) {
                var apptId = appt.apptId;
                var doctor = appt.docName;
                var datetime = appt.dateTime;
                var totaldue = appt.totalDue;
                var duedate = appt.dueDate;

                var row = '<tr>';
                    row += '<td>' + apptId + '</td>';
                    row += '<td>' + doctor + '</td>';
                    row += '<td>' + datetime + '</td>';
                    row += '<td>$' + totaldue + '</td>';
                    row += '<td>' + duedate + '</td>';
                    row += '<td><a onclick="showEditApptForm(' + apptId + ')">Reschedule</a></td>';
                    row += '<td><a onclick="cancelAppointment(' + apptId + ')">Cancel</a></td>';
                    row += '</tr>';
                contentRows.append(row);
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

function clearAppointmentsTable() {
    $('#contentRows').empty();
}

function showEditProfile(patId) {

    // clear errorMessages
    $('#errorMessages').empty();
    // get the contact details from the server and then fill and show the
    // form on success
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/luxMD/myCare/Patient/' + patId,
        success: function(data, status) {
              $('#edit-profile-fname').val(data.firstName);
              $('#edit-profile-lname').val(data.lastName);
              $('#edit-profile-phoneNumber').val(data.phoneNumber);
              $('#edit-profile-streetAddress').val(data.streetAddress);
              $('#edit-profile-city').val(data.city);
              $('#edit-profile-state').val(data.state);
              $('#edit-profile-zipCode').val(data.zipCode);
          },
          error: function() {
            $('#errorMessages')
               .append($('<li>')
               .attr({class: 'list-group-item list-group-item-danger'})
               .text('Error calling web service.  Please try again later.'));
          }
    });
    $('#createAppointment').hide();
    $('#apptsTableDiv').hide();
    $('#editProfile').show();
}

function hideEditProfile() {
    // clear errorMessages
    $('#errorMessages').empty();
    // clear the form and then hide it
    $('#edit-profile-fname').val('');
    $('#edit-profile-lname').val('');
    $('#edit-profile-phoneNumber').val('');
    $('#edit-profile-streetAddress').val('');
    $('#edit-profile-city').val('');
    $('#edit-profile-state').val('');
    $('#edit-profile-zipCode').val('');
    $('#editProfile').hide();
    $('#createAppointment').show();
    $('#apptsTableDiv').show();
}

function showEditApptForm(apptId) {
    // clear errorMessages
    $('#errorMessages').empty();
    // get the contact details from the server and then fill and show the
    // form on success
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/luxMD/myCare/myAppointment/' + apptId,
        success: function(data, status) {
              $('#edit-apptId').val(apptId);
              $('#edit-datepicker').val(data.date);
              $('#edit-timepicker').val(data.time);
              $('#edit-notes').val(data.notes);
          },
          error: function() {
            $('#errorMessages')
               .append($('<li>')
               .attr({class: 'list-group-item list-group-item-danger'})
               .text('Error calling web service.  Please try again later.'));
          }
    });
    $('#createAppointment').hide();
    $('#editApptFormDiv').show();
}

function hideEditApptForm() {
    // clear errorMessages
    $('#errorMessages').empty();
    // clear the form and then hide it
    $('#edit-apptId').val('');
    $('#edit-datepicker').val('');
    $('#edit-timepicker').val('');
    $('#edit-notes').val('');
    $('#editApptFormDiv').hide();
    $('#createAppointment').show();
}

function cancelAppointment(apptId) {
    console.log(apptId);
    $.ajax ({
        url: 'http://localhost:8080/api/luxMD/myCare/cancelAppointment/' + apptId,
        type: 'DELETE',
        crossDomain: true,
        headers: {
            "accept" : "application/json",
            "Access-Control-Origin" : "*",
            "Access-Control-Methods" : "POST,GET,OPTIONS,PUT,DELETE",
            "Access-Control-Allow-Headers" : "Content-Type"
        },
        success: function (status) {
            loadAppointments();
            alert("Appointment cancelled.");
        }
    });
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
