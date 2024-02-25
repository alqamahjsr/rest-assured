

package data.reqres;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostData {

    private final String name;
    private final String job;

    /**
     * Created By Alquamah Shahid
     *
     * @param name - mandatory field for post request
     * @param job - mandatory field for post request
     */
    public PostData (final String name, final String job) {
        this.name = name;
        this.job = job;

    }
}