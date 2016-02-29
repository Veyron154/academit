$(document).ready(function () {
    var sliders = $(".slider");
    var inputs = $(".main-table td:nth-child(6) input");

    for (var i = 0; i < sliders.length; ++i) {
        sliders.eq(i).slider({
            min: 0,
            max: 1000,
            step: 10,
            slide: function (x) {
                return function () {
                    var sliderValue = sliders.eq(x).slider("value");
                    inputs.eq(x).val(sliderValue);
                    if (sliderValue >= 100) {
                        sliders.eq(x).slider("option", "step", 100);
                    } else {
                        sliders.eq(x).slider("option", "step", 10);
                    }
                };
            }(i),
            stop: function (x) {
                return function () {
                    var sliderValue = sliders.eq(x).slider("value");
                    inputs.eq(x).val(sliderValue);
                    if (sliderValue === 0) {
                        sliders.eq(x).slider("option", "step", 10);
                    }
                };
            }(i)
        });

        inputs.eq(i).change(function (x) {
            return function () {
                var inputValue = inputs.eq(x).val();
                if (inputValue % 10 !== 0) {
                    inputs.eq(x).val(inputValue - (inputValue % 10));
                }
                sliders.eq(x).slider("value", inputValue);
            };
        }(i));
    }
});
