var locate = window.location;
$('#global_id').val(locate);
var url = $('#global_id').val();
var docId_global = url.substr(url.indexOf("?") + 1);

$(document).ready(function() {
    loadDoctor();
    loadAppointments();
});

function loadDoctor() {

    $.ajax ({
        type: 'GET',
        url: 'http://localhost:8080/api/luxMD/ourPC/Doctor/' + docId_global,
        success: function (doctor, status) {
            var docId = doctor.doctorId;
            var firstName = doctor.firstName;
            var lastName = doctor.lastName;
            $("#intro").append("Welcome " + firstName + " " + lastName + "! (ID: " + docId_global + ")");
            //$("#editProf").append('<a onclick="showEditProfile(' + docId + ')">Edit Doctor Profile</a>')
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
        url: 'http://localhost:8080/api/luxMD/ourPC/myAppointments/' + docId_global,
        success: function (data, status) {
            data = data.sort(function(a, b) {
                var first = new Date(a.dateTime);
                var second = new Date(b.dateTime);
                return ( first > second ) ? 1 : ( first < second ) ? -1 : 0;
            });
            $.each(data, function (index, appt) {
                var apptId = appt.apptId;
                var patId = appt.patId;
                var patient = appt.patName;
                var datetime = appt.dateTime;
                var notes = appt.notes;

                var row = '<tr>';
                    row += '<td>' + apptId + '</td>';
                    row += '<td><a onclick="showPatient(' + patId + ')">' + patient + '</a></td>';
                    row += '<td>' + datetime + '</td>';
                    row += '<td>' + notes + '</td>';
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

function showPatient(patId) {

    // clear errorMessages
    $('#errorMessages').empty();
    $('#patient_profile').empty();
    // get the contact details from the server and then fill and show the
    // form on success
    var patientInternalNote;
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/luxMD/myCare/Patient/' + patId,
        success: function(patient, status) {

              var patName = patient.firstName + " " + patient.lastName;
              var patPhoneNumber = patient.phoneNumber;
              var patAddress = patient.streetAddress + ", " + patient.city + ", " + patient.state + " " + patient.zipCode;
              patientInternalNote = patient.internalNote;
              var profile = '<h4><center><strong>Patient ID: '+patId+'</strong></center></h4>';
              profile += '<h4><center><strong>Name: '+patName+'</strong></center></h4>';
              profile += '<h5><center><strong>Phone Number: '+patPhoneNumber+'</strong></center></h5>';
              profile += '<h5><center><strong>Address: '+patAddress+'</strong></center></h5>';
              profile += '<br />';
              profile += '<label for="internalNotes">Internal oPC Notes For '+patName+':</label>'
              profile += '<textarea class="form-control" rows="5" id="internalNotes">'+patientInternalNote+'</textarea>';
              profile += '<br />';
              profile += '<div class="col-md-offset-4 col-md-4"><button type="button" id="update-internal" class="btn btn-default" onclick="updateInternal('+patId+')">';
              profile += 'Update Internal Notes';
              profile += '</button></div>';
              profile += '<br />';
              profile += '<br />';
              profile += '<br />';
              profile += '<table id="apptsHistoryTable" class="table table-hover">';
              profile += '<tr><th width="10%">Appt #</th><th width="20%">Doctor</th><th width="20%">Care Type</th><th width="20%">Date & Time</th><th width="30%">Notes</th></tr>';
              profile += '<tbody id="historyRows"></tbody>'
              $('#patient_profile').append(profile);
              loadPatientHistory(patId);
              tableEnd = '</table>';
              $('#patient_profile').append(tableEnd);

          },
          error: function() {
            $('#errorMessages')
               .append($('<li>')
               .attr({class: 'list-group-item list-group-item-danger'})
               .text('Error calling web service.  Please try again later.'));
          }
    });
}

function updateInternal(patId) {
    var internalNote = $('#internalNotes').val();
    $.ajax({
       type: 'POST',
       url: 'http://localhost:8080/api/luxMD/ourPC/editInternalNote/' + patId,
       data: internalNote,
       headers: {
         'Accept': 'application/json',
         'Content-Type': 'application/json'
       },
       'dataType': 'json',
        success: function(data, status) {
            // clear errorMessages
            $('#errorMessages').empty();
            alert("Internal Notes Updated.");
            showPatient(patId);
       },
       error: function(error) {
         $('#errorMessages')
            .append($('<li>')
            .attr({class: 'list-group-item list-group-item-danger'})
            .text('Error calling web service.  Please try again later.'));
       }
   });
}

function loadPatientHistory(patId) {

    $.ajax ({
        type: 'GET',
        url: 'http://localhost:8080/api/luxMD/myCare/myAppointments/' + patId,
        success: function (data, status) {
            data = data.sort(function(a, b) {
                var first = new Date(a.dateTime);
                var second = new Date(b.dateTime);
                return ( first > second ) ? 1 : ( first < second ) ? -1 : 0;
            });
            $.each(data, function (index, appt) {
                var apptId = appt.apptId;
                var doctor = appt.docName;
                var careType = appt.careType;
                var datetime = appt.dateTime;
                var notes = appt.notes;

                var row = '<tr>';
                    row += '<td>' + apptId + '</td>';
                    row += '<td>' + doctor + '</td>';
                    row += '<td>' + careType + '</td>';
                    row += '<td>' + datetime + '</td>';
                    row += '<td>' + notes + '</td>';
                    row += '</tr>';
                $('#historyRows').append(row);
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
