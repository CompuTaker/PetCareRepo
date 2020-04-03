

// 업체찾기 목록
var beauty = document.getElementById("beauty");
var hospital = document.getElementById("hospital");
var hotel = document.getElementById("hotel");

var service = document.getElementById("Reservation_Type");

hospital.style.display = "none";
hotel.style.display = "none";

service.addEventListener('click', function (event) {
    if (event.target.value == "beauty") {
        beauty.style.display = "block";
        hospital.style.display = "none";
        hotel.style.display = "none";
    } else if (event.target.value == "hospital") {
        hospital.style.display = "block";
        beauty.style.display = "none";
        hotel.style.display = "none";
    } else if (event.target.value == "hotel") {
        hotel.style.display = "block";
        beauty.style.display = "none";
        hospital.style.display = "none";
    }
});

