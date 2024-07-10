<script th:inline="javascript">
    function previewImage(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (event) {
                $('#imagePreview').attr('src', event.target.result);
                $('#imagePreview').css('display', 'block');
            };

            reader.readAsDataURL(input.files[0]);
        }
    }
</script>