package com.workintech.zoo.exceptions;


import lombok.*;

//status: Her hatanın bir http status kodu olur
//200 => OK (200 -> get, 201 -> Created POST için)
//400(Bad Request) => Client exceptions datanın gelmesinde problem var, data beklediğimiz şekilde gelmesi
//500 => Server Errors, sunucuda bir hata oluştur, NullPointerExcetion, backendden kaynaklı bir hata olur.
//Örnek olarak ArrayIndexOutBoundException ve bu tarz hatalar geldiğinde 500 hatası gelecektir.
//404 => url yanlış girildiğinde ya da dosya bulunamadığında gelen hata

@NoArgsConstructor
@AllArgsConstructor
@Data
//Bu sınıf bizim clientımıza döneceğimiz bilgileri taşıyor.****
public class ZooErrorResponse {

    private int status;      //HTTP status değeri
    private String message;  //Hata fırlatıldığında hata için yazılacak mesaj
    private long timeStamp;//Hatanın fırlatıldığı zaman anı

    public long getTimestamp() {
        return timeStamp;
    }

    public void setTimestamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
