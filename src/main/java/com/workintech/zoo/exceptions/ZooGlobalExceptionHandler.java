package com.workintech.zoo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


//Bu sınıfın görevi sistemde olan tüm hataları yakalamak
//Tek izlediği takip ettiği şey sistemde yaşanan tüm hataları takip etmek
//Bu tarz sınıflara tüm sınıfları tarasın izlesin belli durumlarda araya girsin ve bir işlem yapsın
//istediğimiz sınıflara Intercepter sınıflar denir.
//Intercepter => interrupt etmekten geliyor o da araya girmek demek.
//Sistemimiz çalışırken bir hata olma durumunda diyeceğiz ki bu sınıfa sen hataları izleyen bir sınıfsın
//senin görevin bu projede olan bütün hataları izlemek.
//Bu sınıfın görevi: Kod satır satır çalışıyor, bir yerde bir hata olduğu an hemen araya girip intercept
//edicek class olarak araya girecek ve ondan sonrası devam etmeyecek ve buradan bir return dönecek bize.
//Bir hata oldu diyecek ve o hataya karşı yapması gereken işlemi yapacak.
//ControllerAdvice bir adviser olarak çalışıp intercept edeceksin demek.


@ControllerAdvice
public class ZooGlobalExceptionHandler {


    //ResponseEntity ile ZooErrorResponse dönüyoruz.
    //Bir response olacağı için ResponseEntity'i kullanıyoruz.
    //Burada zooException türünde hata oluştuğu an direk bu methodu çalıştıracaksın şeklinde işlem yapıyoruz.

    @ExceptionHandler(ZooException.class)
    public ResponseEntity<ZooErrorResponse> handleException(ZooException zooException){
        ZooErrorResponse errorResponse = new ZooErrorResponse(
                zooException.getHttpStatus().value(),
                zooException.getMessage(),
                System.currentTimeMillis());
    return new ResponseEntity<>(errorResponse, zooException.getHttpStatus());
    }


    //üstteki hatayı handle edemezse burası çalışacaktır

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ZooErrorResponse> handleException(Exception exception){
        ZooErrorResponse errorResponse = new ZooErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }


}
