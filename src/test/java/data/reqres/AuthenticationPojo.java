

package data.reqres;

import lombok.Data;

@Data
public class AuthenticationPojo {

    private String email;
    private String password;

    /**
     * Created By Alquamah Shahid
     *
     * @param email for login
     * @param password for login
     */
    public AuthenticationPojo (String email, String password) {
        this.email = email;
        this.password = password;
    }

}