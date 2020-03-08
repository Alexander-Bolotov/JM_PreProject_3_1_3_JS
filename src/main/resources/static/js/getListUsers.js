$(document).ready(function () {
    $.getJSON('/users', function (json) {
        let tr = [];
        for (let i = 0; i < json.length; i++) {
            tr.push('<tr>');
            tr.push('<td id=' + json[i].id + '>' + json[i].id + '</td>');
            tr.push('<td name=' + json[i].name + '>' + json[i].name + '</td>');
            tr.push('<td password=' + json[i].password + '>' + json[i].password + '</td>');
            tr.push('<td roles=' + json[i].roles.map(role => role.role) + '>' + json[i].roles.map(role => role.role) + '</td>');
            tr.push('<td id ='+"edit" + json[i].id + '><button type="button" class="btn-primary" data-toggle="modal" data-target="#myModal" id='+"edit" + json[i].id + '>UPDATE</button>&nbsp;&nbsp;<button class=\'delete\' id=' + json[i].id + '>Delete</button></td>');
            tr.push('</tr>');
        }
        $("#tableUserList").append($(tr.join('')));
    });



    $(document).delegate('button[id^=edit]', 'click',function () {

        let atrId = $(this).parent().prev().prev().prev().prev().attr("id");            //id
        let atrName = $(this).parent().prev().prev().prev().attr("name");               //name
        let atrPassword = $(this).parent().prev().prev().attr("password");              //password

        $('input[name=id]').prop("value", atrId);

        $('input[name=name]').prop("value", atrName);

        $('input[name=password]').prop("value", atrPassword);

            $("#myModalBox").modal('show');

    });

    $(document).delegate('.delete', 'click', function () {
        if (confirm('Do you really want to delete  User with id ?')) {
            let id = $(this).parent().prev().prev().prev().prev().attr("id");
            let parent = $(this).parent().parent();
            $.ajax({
                method: "DELETE",
                url: "/user/"+id,
                cache: false,
                success: function () {
                    parent.fadeOut('slow', function () {
                        $(this).remove();
                    });
                    location.reload(true)
                },
                error: function () {
                    $('#err').html('<span style=\'color:red; font-weight: bold; font-size: 30px;\'>Error deleting record').fadeIn().fadeOut(4000, function () {
                        $(this).remove();
                    });
                }
            });
        }
    });

    $('#addUser').submit(function () {
        $.post(
            '/user',                                    // адрес обработчика
            $("#addUser").serialize(),              // отправляемые данные
            function (msg) {                        // получен ответ сервера
                document.location.href = msg;
            }
        );
        return false;
    });

// -->Редактирование пользователя:

    $('#modal_close').click(function () {
        $.get(
            '/user',                              // адрес обработчика
            $('#editUser').serialize(),               // отправляемые данные
            function (msg) {                          // получен ответ сервера
                document.location.href = msg;
            }
        );
        return false;
    });


    $.ajax({
        method: "GET",
        url: "/userRole/",
        cache: false,
        success: function (msg) {
            // alert(msg);v-pills-tabContent
            if(msg=="USER") {
                document.getElementById("v-pills-home-tab").setAttribute('aria-selected', "false");
                document.getElementById("v-pills-profile-tab").setAttribute('aria-selected', "true");
                document.getElementById("v-pills-home-tab").style.display = 'none';

                document.getElementById("v-pills-profile-tab").style.display = 'block';
            }

        },

    });

});
