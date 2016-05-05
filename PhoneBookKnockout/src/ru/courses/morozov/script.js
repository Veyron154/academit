$(document).ready(function () {
    var vm = new PhoneBookViewModel();
    ko.applyBindings(vm);
});

function PhoneBookViewModel() {
    var self = this;
    self.tableItems = ko.observableArray([]);
    self.visibleTableItems = ko.observableArray([]);
    self.name = ko.observable("");
    self.surname = ko.observable("");
    self.phone = ko.observable("");
    self.isTopChecked = ko.observable(false);
    self.filterText = ko.observable("");
    self.needValidate = ko.observable(false);

    self.isTopChecked.subscribe(function (newValue) {
        _.each(self.tableItems(), function (item) {
            item.isChecked(newValue);
        });
    });

    self.addTableItem = function () {
        self.needValidate(true);

        if (self.surname() === "" || self.name() === "" || self.phone() === "") {
            $.alert({
                title: "Ошибка заполнения",
                content: "Заполните выделенные поля",
                confirmButton: "OK"
            });
            return;
        }

        var isUniquePhone = true;
        _.each(self.tableItems(), function (item) {
            if (item.itemPhone === self.phone()) {
                isUniquePhone = false;
            }
        });

        if (!isUniquePhone) {
            $.alert({
                title: "Ошибка заполнения",
                content: "Контакт с номером " + self.phone() + " уже существует",
                confirmButton: "OK"
            });
            return;
        }

        var addedItem = new TableItemsViewModel(self.name(), self.surname(), self.phone());
        self.tableItems.push(addedItem);
        self.visibleTableItems.push(addedItem);

        self.name("");
        self.surname("");
        self.phone("");
        self.needValidate(false)
    };

    self.removeTableItem = function (item) {
        var rows = [];
        _.each(self.visibleTableItems(), function (item) {
            if (item.isChecked() === true) {
                rows.push(item);
            }
        });
        var messageString = "следующие контакты? <br />";
        _.each(rows, function (i) {
            messageString += i.itemSurname + "<br />";
        });
        if (rows.length === 0) {
            rows.push(item);
            messageString = "контакт: " + item.itemSurname + " ?";
        }
        $.confirm({
            title: "Подтверждение удаления",
            content: "Вы действительно хотите удалить " + messageString,
            confirmButton: "OK",
            cancelButton: "Отмена",
            confirm: function () {
                self.tableItems.removeAll(rows);
                self.visibleTableItems.removeAll(rows);
            }
        });
    };

    self.executeFilter = function () {
        var filter = self.filterText().toLowerCase();
        _.each(self.tableItems(), function (item) {
            if (item.itemSurname.toLowerCase().indexOf(filter) === -1 &&
                item.itemName.toLowerCase().indexOf(filter) === -1 &&
                item.itemPhone.toLowerCase().indexOf(filter) === -1) {
                self.visibleTableItems.remove(item);
            }
        });
    };

    self.cancelFilter = function () {
        for (var i = 0; i < self.tableItems().length; ++i) {
            self.visibleTableItems()[i] = self.tableItems()[i];
        }
        self.visibleTableItems.valueHasMutated();
        self.filterText("");
    }
}

function TableItemsViewModel(name, surname, phone) {
    var self = this;

    self.itemName = name;
    self.itemSurname = surname;
    self.itemPhone = phone;

    self.isChecked = ko.observable(false);
}






