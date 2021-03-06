$(document).ready(function () {
    $(".submit").click(function (e) {
        e.preventDefault();

        var inputs = $(".input-phone-book");
        var isCorrect = true;

        inputs.each(function () {
            if ($(this).val() == "") {
                $(this).parents(".form-inline").addClass("has-error");
                isCorrect = false;
            } else {
                $(this).parents(".form-inline").removeClass("has-error");
            }
        });

        if (!isCorrect) {
            $.alert({
                title: "Ошибка заполнения",
                content: "Заполните выделенные поля",
                confirmButton: "OK"
            });
            return;
        }

        var surname = $("#surname");
        var name = $("#name");
        var phone = $("#phone");

        var isUniquePhone = true;
        var phones = $(".table-phone-book tbody .fifth-column");

        phones.each(function () {
            if ($(this).text() === phone.val()) {
                isUniquePhone = false;
                return false;
            }
        });

        if (!isUniquePhone) {
            $.alert({
                title: "Ошибка заполнения",
                content: "Контакт с номером " + phone.val() + " уже существует",
                confirmButton: "OK"
            });
            return;
        }

        var tr = $("<tr></tr>");
        $(".table-phone-book tbody").append(tr);
        var checkBox = $("<input />").attr("type", "checkbox");
        tr.append($("<td class='first-column'></td>").html(checkBox));
        var tdIndex = $("<td class='second-column'></td>");
        tr.append(tdIndex);
        var tdSurname = $("<td class='third-column'></td>").text(surname.val());
        tr.append(tdSurname);
        var tdName = $("<td class='fourth-column'></td>").text(name.val());
        tr.append(tdName);
        var tdPhone = $("<td class='fifth-column'></td>").text(phone.val());
        tr.append(tdPhone);
        var deleteButton = $("<button type='button' class='btn btn-default'></button>").click(function () {
            var rows = $(".table-phone-book tr:has(td [type='checkbox']:checked):visible");
            var messageString = "следующие контакты? <br />";
            $(".table-phone-book tr:has(td [type='checkbox']:checked) .third-column").each(function () {
                messageString += $(this).text() + "<br />";
            });
            if (rows.length === 0) {
                rows = $(this).parents("tr");
                messageString = "контакт: " + $(this).parents("tr").children(".third-column").text() + " ?";
            }

            $.confirm({
                title: "Подтверждение удаления",
                content: "Вы действительно хотите удалить " + messageString,
                confirmButton: "OK",
                cancelButton: "Отмена",
                confirm: function () {
                    rows.remove();
                    renumberRow();
                }
            });
        });
        deleteButton.html("<span class='glyphicon glyphicon-remove-sign' aria-hidden='true'></span>");
        tr.append($("<td class='sixth-column'></td>").html(deleteButton));

        renumberRow();

        inputs.each(function () {
            $(this).parents(".form-group").removeClass("has-error");
            $(this).val("");
        });

        $(".button-filter-clear").trigger("click");
    });

    $(".top-checkbox").change(function () {
        var checkboxes = $(".table-phone-book tr td:first-child [type='checkbox']");
        checkboxes.prop("checked", $(this).prop("checked"));
    });

    $(".button-filter-ok").click(function () {
        var rows = $(".table-phone-book tbody tr");
        rows.hide();
        var tableData = $(".table-phone-book tbody tr td");
        var filterText = $(".text-filter").val().toLowerCase();
        tableData.each(function () {
            if ($(this).text().toLowerCase().indexOf(filterText) !== -1) {
                $(this).parents("tr").show();
            }
        });
    });

    $(".button-filter-clear").click(function () {
        var rows = $(".table-phone-book tbody tr");
        rows.show();
        $(".text-filter").val("");
    });
});

var renumberRow = function () {
    var rowsIndices = $(".table-phone-book tbody .second-column");
    rowsIndices.each(function (i) {
        $(this).text(i + 1);
    });
};
