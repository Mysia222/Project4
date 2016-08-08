package tag;

import dao.DAOException;
import ent.Service;
import services.ServService;
import services.SubService;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Created by Славик on 07.08.2016.
 */
public class ServicesTag extends SimpleTagSupport {

    private SubService service;
    private String bundleSubsSize;





    @Override
    public void doTag() throws JspException, IOException {
        Writer out = getJspContext().getOut();
        try {
            List list = service.getSubsList();
            out.write(bundleSubsSize + list.size());
        } catch (DAOException e) {
            e.printStackTrace();
        }



    }

    public String getBundleSubsSize() {
        return bundleSubsSize;
    }

    public void setBundleSubsSize(String bundleSubsSize) {
        this.bundleSubsSize = bundleSubsSize;
    }

    public SubService getService() {
        return service;
    }

    public void setService(SubService service) {
        this.service = service;
    }
}
