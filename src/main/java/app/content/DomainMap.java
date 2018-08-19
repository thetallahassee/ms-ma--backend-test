package app.content;

import java.util.List;

public class DomainMap {
    List<Domain> domainList;


}

class Domain {
    private Class classType;
    private Class controller;

    public Domain(Class classType, Class controller) {
        this.classType = classType;
        this.controller = controller;
    }
}
