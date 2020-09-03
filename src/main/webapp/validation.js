function get(){
    const inputValue = $("#input").val()
    $.ajax
    ({
        type: "POST",
        url: '/InputServlet',
        data: {input:inputValue},
        dataType:'text',
        success : function(data) {
            $("#outputResult").val(data);
        },
        error : function ajaxFailure(data, textStatus, errorThrown){
            alert("OH NO! data: "+data + ' textStatus: ' + textStatus + 'errorThrown: ' + errorThrown);
        }
    });
}

function checkInput(){
    let focusSet = false;
    const data = $("#input").val();
    const regx = /^[0-9]+(,[0-9]+)*$/;

    console.log( data + ' patt:'+ data.match(regx));

    if ( data === '' || data.match(regx) ){
        $("#input").parent().next(".validation").remove();
        $("#submit").prop('disabled', false);
    } else {
        if ($("#input").parent().next(".validation").length === 0) // only add if not added
        {
            $("#input").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Only allow number and comma. Each number must be separated by comma</div>");
        }
        $("#submit").prop('disabled', true);
        $('#input').focus();
        focusSet = true;
    }
}