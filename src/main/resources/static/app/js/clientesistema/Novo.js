$(function () {
  $('#cep').focusout(function () {
    if ($("#cep").val()) {
      var cep = $("#cep").val();
      cep = cep.replace("-", "");
      $.get("https://viacep.com.br/ws/" + cep + "/json/", function (data) {
        if (data) {
          $("#bairro").val(data.bairro);
          $("#estado").val(data.uf);
          $("#cidade").val(data.localidade);
          $("#logradouro").val(data.logradouro);
        }
      });
    }
  });
});