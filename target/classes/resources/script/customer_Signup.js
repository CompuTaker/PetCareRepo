(function($) {
	"use strict";
$("#checkId").click(function () {
  var customer_Id = $("#Customer_Id").val();
  $.ajax({
    url:
      "${pageContext.request.contextPath}/customer_checkId?customer_Id=" +
      customer_Id,
    method: "GET",
    async: false,
    complete: function (data) {
      if (data.responseText == 1) {
        $("#id_check").text("중복입니다.");
        $("#id_check").css("color", "red");
        $("#Customer_Id").val("");
      } else {
        $("#id_check").text("사용가능합니다.");
        $("#id_check").css("color", "blue");
        $("#Customer_Id").attr("readonly", true);
        $("#checkId").attr("disabled", true);
      }
    },
  });
});

$("#checkResidentNum").click(function () {
  var customer_ResidentNumber = $("#Customer_ResidentNumber").val();
  $.ajax({
    url:
      "${pageContext.request.contextPath}/customer_chekResidentNumber?customer_ResidentNumber=" +
      customer_ResidentNumber,
    method: "GET",
    async: false,
    complete: function (data) {
      if (data.responseText == 1) {
        $("#residentNum_check").text("중복입니다.");
        $("#residentNum_check").css("color", "red");
        $("#Customer_ResidentNumber").val("");
      } else {
        $("#residentNum_check").text("사용가능합니다.");
        $("#residentNum_check").css("color", "blue");
        $("#Customer_ResidentNumber").attr("readonly", true);
        $("#checkResidentNum").attr("disabled", true);
      }
    },
  });
});
})(jQuery);