package springMVC_RestAPI.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//аннотация  @RestControllerAdvice в частсности говорит, что при выбросе исключений (в нашем примере мы их выбрасываем в контроллере)
//будут смотреться методы текущего класса, на предмет того, могут ли они обработать исключеие
@RestControllerAdvice
public class GlobalExceptionHandler {

    //обработка исключений
    //сперва указывается аннотация @ExceptionHandler
    //ResponseEntity - это обертка http ответа, то есть в принципе это http ответ с сервера
    //в генерике указываем какой тип объекта будет добавляться в http response body (тело http ответа)
    //в параметрах указываем при выбросе какого исключения должен сработать метод-обработчик исключений
    //возвращаем соответственно ResponseEntity, указывая в параметрах объект, который использовался для генерика
    //и статус хттп ответа

    //в данном примере при выбросе исключения NoSuchEmployeeException будет вызван данный метод
    //который вернет класс infoAboutException ( помещенный в обертку ResponseEntity) и в этот класс мы установили
    // поле информация -  которое будет выведено в формете json'a
    @ExceptionHandler  //аннотация для метода перехватывающего исключения
    public ResponseEntity<InformationAboutException> handlerException (NoSuchEmployeeException exception)
    {
        InformationAboutException infoAboutException = new InformationAboutException();
        infoAboutException.setInfo(exception.getMessage());

        return new ResponseEntity<>(infoAboutException, HttpStatus.NOT_FOUND);
    }

    //перегрузка метода
    @ExceptionHandler  //аннотация для метода перехватывающего исключения
    public ResponseEntity<InformationAboutException> handlerException (Exception exception)
    {
        InformationAboutException infoAboutException = new InformationAboutException();
        infoAboutException.setInfo(exception.getMessage());

        return new ResponseEntity<>(infoAboutException, HttpStatus.BAD_REQUEST);
    }
}
