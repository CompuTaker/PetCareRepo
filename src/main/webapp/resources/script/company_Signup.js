(function ($) {
  "use strict";
  $("#checkCompanyId").click(function () {
    var company_Id = $("#Company_Id").val();
    $.ajax({
      url:
        "${pageContext.request.contextPath}/company_checkcId?company_Id=" +
        company_Id,
      method: "GET",
      async: false,
      complete: function (data) {
    	 console.log(company_Id)
        if (data.responseText == 1) {
          $("#companyId_check").text("중복입니다.");
          $("#companyId_check").css("color", "red");
          $("#Company_Id").val("");
        } else {
          $("#companyId_check").text("사용가능합니다.");
          $("#companyId_check").css("color", "blue");
          $("#Company_Id").attr("readonly", true);
          $("#checkCompanyId").attr("disabled", true);
        }
      },
    });
  });
  $("#checkCompanyNum").click(function () {
    var company_Number = $("#Company_Number").val();
    $.ajax({
      url:
        "${pageContext.request.contextPath}/company_checkComNum?company_Number=" +
        company_Number,
      method: "GET",
      async: false,
      complete: function (data) {
        if (data.responseText == 1) {
          $("#companyNum_check").text("중복입니다.");
          $("#companyNum_check").css("color", "red");
          $("#Comapny_Number").val("");
        } else {
          $("#companyNum_check").text("사용가능합니다.");
          $("#companyNum_check").css("color", "blue");
          $("#Company_Number").attr("readonly", true);
          $("#checkCompanyNum").attr("disabled", true);
        }
      },
    });
  });
})(jQuery);
