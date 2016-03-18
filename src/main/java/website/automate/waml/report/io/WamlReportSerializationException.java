package website.automate.waml.report.io;

public class WamlReportSerializationException extends RuntimeException {

    private static final long serialVersionUID = 3262269456525705952L;

    public WamlReportSerializationException(String msg, Throwable e){
        super(msg, e);
    }
}
