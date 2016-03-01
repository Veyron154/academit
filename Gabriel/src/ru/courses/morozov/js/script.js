$(document).ready(function () {
    var sliders = $(".slider");
    var inputs = $(".main-table td:nth-child(6) input");

    for (var i = 0; i < sliders.length; ++i) {
        var maxValue = 1000;
        var minValue = 0;
        var minStep = 10;
        sliders.eq(i).slider({
            min: minValue,
            max: maxValue,
            step: minStep,
            slide: function (x) {
                return function () {
                    var sliderValue = sliders.eq(x).slider("value");
                    inputs.eq(x).val(sliderValue);
                    var stepSwitchValue = 100;
                    if (sliderValue >= stepSwitchValue) {
                        var maxStep = 100;
                        sliders.eq(x).slider("option", "step", maxStep);
                    }
                };
            }(i),
            stop: function (x, minValue, minStep) {
                return function () {
                    var sliderValue = sliders.eq(x).slider("value");
                    inputs.eq(x).val(sliderValue);
                    if (sliderValue === minValue) {
                        sliders.eq(x).slider("option", "step", minStep);
                    }
                };
            }(i, minValue, minStep)
        });

        inputs.eq(i).change(function (x, maxValue) {
            return function () {
                var inputValue = inputs.eq(x).val();
                var valueCoefficient = 10;
                if (inputValue % valueCoefficient !== 0) {
                    inputs.eq(x).val(inputValue - (inputValue % valueCoefficient));
                }
                if (inputValue > maxValue) {
                    inputs.eq(x).val(maxValue);
                }
                sliders.eq(x).slider("value", inputValue);
            };
        }(i, maxValue));

        inputs.eq(i).keypress(function (e) {
            var chr = String.fromCharCode(e.which);
            if (chr < '0' || chr > '9') {
                return false;
            }
        });
    }

    $(".main-table .scroll-pane").jScrollPane({
        verticalDragMaxHeight: 27
    });

    $(".table-previous-application .scroll-pane").jScrollPane({
        verticalDragMaxHeight: 14
    });

});
