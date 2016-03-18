package website.automate.waml.report.io;

public class WamlReportDeserializationException extends RuntimeException {

    private static final long serialVersionUID = 3262269456525705952L;

    public WamlReportDeserializationException(String msg, Throwable e){
        super(msg, e);
    }
}
