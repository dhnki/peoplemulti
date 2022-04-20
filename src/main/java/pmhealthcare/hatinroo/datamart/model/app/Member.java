package pmhealthcare.hatinroo.datamart.model.app;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "members")
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ToString
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private long no;

    @Column(name = "email", unique = true )
    private String email;

    @JsonIgnore
    private String password;

    private byte tempPasswordYn;

//    private Long memberImageNo;

    private String name;

    private LocalDate birthday;

    private String phone;

    private String moniterImageUrl;

    private String moniterName;

    private LocalDate moniterBirthday;

    private byte moniterGender;

    private String moniterDisease;

    @JsonIgnore
    private byte pushYn;

    @JsonIgnore
    private byte autoLoginYn;

    @JsonIgnore
    private byte agreeMarketing;

    @JsonIgnore
    private LocalDateTime regdate;

    @JsonIgnore
    private byte validity;


    public void join(String email, String password, String name, LocalDate birthday, String phone, String moniterName, LocalDate moniterBirthday, byte moniterGender, String moniterDisease, byte agreeMarketing) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.phone = phone;
        this.agreeMarketing = agreeMarketing;
        this.tempPasswordYn = (byte) 0;

        this.pushYn = (byte) 1;
        this.autoLoginYn = (byte) 1;

        this.regdate = LocalDateTime.now();
        this.validity = (byte) 1;

        this.moniterName = moniterName;
        this.moniterBirthday = moniterBirthday;
        this.moniterGender = moniterGender;
        this.moniterDisease = moniterDisease;


    }

    public void update(String name, String phone, LocalDate birthday ){
        this.name = name;
        this.phone = phone;
        this.birthday =birthday;
    }

    public void updateInfo(String name, String phone, LocalDate birthday, String moniterName, LocalDate moniterBirthday, byte moniterGender, String moniterDisease ){
        this.name = name;
        this.phone = phone;
        this.birthday =birthday;
        this.moniterName = moniterName;
        this.moniterBirthday = moniterBirthday;
        this.moniterGender = moniterGender;
        this.moniterDisease = moniterDisease;
    }

    public void updateMoniter(String moniterImageUrl, String moniterName, byte moniterGender,  LocalDate moniterBirthday, String moniterDisease ){
        this.moniterImageUrl = moniterImageUrl;
        this.moniterName =moniterName;
        this.moniterBirthday = moniterBirthday;
        this.moniterGender = moniterGender;
        this.moniterDisease = moniterDisease;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void changeTempPassword(String password){
        this.password = password;
        this.tempPasswordYn = (byte) 0;
    }

    public void setTempPassword(String password){
        this.password = password;
        this.tempPasswordYn = (byte) 1;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void withdraw(){
//        this.email = null;
        this.validity = (byte) 0;
    }

    public void changePushYn(){
        this.pushYn = (this.pushYn == 1) ? (byte) 0 : (byte) 1;
    }

    public void changeAutoLogin(){
        this.autoLoginYn = (this.autoLoginYn == 1) ? (byte) 0 : (byte) 1;
    }

}
