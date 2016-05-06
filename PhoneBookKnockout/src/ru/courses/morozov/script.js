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

    var isFiltered = false;

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

        var isUniquePhone = _.every(self.tableItems(), function (item) {
            return item.itemPhone !== self.phone();
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

        if (isFiltered) {
            self.executeFilter();
        } else {
            self.visibleTableItems(self.tableItems());
        }

        self.name("");
        self.surname("");
        self.phone("");
        self.needValidate(false)
    };

    self.removeTableItem = function (item) {
        var rows = _.filter(self.visibleTableItems(), function (item) {
            return item.isChecked() === true;
        });
        var messageString = "следующие контакты? <br />";
        messageString += _.pluck(rows, "itemSurname").join("<br />");
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
                self.visibleTableItems(self.tableItems());
            }
        });
    };

    self.executeFilter = function () {
        var filter = self.filterText().toLowerCase();
        self.visibleTableItems(_.filter(self.tableItems(), function (item) {
            return (item.itemSurname.toLowerCase().indexOf(filter) !== -1 ||
            item.itemName.toLowerCase().indexOf(filter) !== -1 ||
            item.itemPhone.toLowerCase().indexOf(filter) !== -1)
        }));
        isFiltered = true;
    };

    self.cancelFilter = function () {
        self.visibleTableItems(self.tableItems());
        self.filterText("");
        isFiltered = false;
    }
}

function TableItemsViewModel(name, surname, phone) {
    var self = this;

    self.itemName = name;
    self.itemSurname = surname;
    self.itemPhone = phone;

    self.isChecked = ko.observable(false);
}






