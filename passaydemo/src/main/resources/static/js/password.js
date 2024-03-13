$(function() {

    $("#createBtn").on("click", function() {

        $.ajax({
            type: "GET",
            url: "/password/create",

        })
        .done(function(data) {
            $("#createdPassword").text(data);
        });
    });




})