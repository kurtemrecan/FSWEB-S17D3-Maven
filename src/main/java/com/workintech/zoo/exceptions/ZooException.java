package com.workintech.zoo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;



//Zoo nesnesi üzerindeki hataları handle etmesi için yazıyoruz.
//Biz şuanda RuntimeExceptiondan türüyen NullPointerExcetion, ClassCastException gibi
//kendi Exception sınıfımızı yazıyoruz.
//Javada hatalar Checked ve Unchecked Exception olarak 2 grupta incelenir.

@Getter
@AllArgsConstructor
@Setter
public class ZooException extends RuntimeException {
  private HttpStatus httpStatus;

  //En sık kullanılan Constructor yapısı bu şekilde mesajı alıp taşımak için kullanıyoruz.
  //Message bilgisi RuntimeException sınıfına gidiyor.
  //Statusüde Exceptionın içerisinde ve dışarısında döneceğiz.

  public ZooException(String message, HttpStatus httpStatus) {
    super(message);
    this.httpStatus = httpStatus;
  }

}
