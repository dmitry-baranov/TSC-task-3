package errors;

public enum Response {

    FILE_NOT_FOUND(1001, "Файл отсутствует!"),
    INVALID_INPUT_DATA(1002, "Входыне данные некорректны!"),
    RESULT_FILE_NOT_FOUND(1003, "Результирующий файл не найден!"),
    UNSUPPORTED_ENCODING_EXCEPTION(1004, "Ошибка кодирования результирующего файла!"),
    NUBER_FORMAT_EXCEPTION(1005, "Неверно указаны данные в исходном файле!");

    private int errorCode;
    private String errorMessage;

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    Response(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }


}
