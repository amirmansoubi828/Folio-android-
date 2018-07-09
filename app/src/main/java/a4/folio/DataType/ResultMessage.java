package a4.folio.DataType;

/**
 * Created by amir on 7/9/2018.
 */

public class ResultMessage {
    private boolean result;
    private String info;

    public ResultMessage(boolean result, String info) {
        this.result = result;
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
