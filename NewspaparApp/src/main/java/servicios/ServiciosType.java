package servicios;

import dao.DaoType;
import jakarta.inject.Inject;
import modelo.Type;

import java.util.ArrayList;
import java.util.List;

public class ServiciosType implements servicios.impl.ServiciosTypeImpl {

    private final DaoType daoType;

    @Inject
    public ServiciosType(DaoType daoType) {
        this.daoType = daoType;
    }


    @Override
    public List<Integer> getAllIdsType() {
        List<Integer> allIds = new ArrayList<>();
        for (int i = 0; i < daoType.getTypeList().size(); i++) {
            allIds.add(daoType.getTypeList().get(i).getId());
        }
        return allIds;
    }

    @Override
    public List<Type> getTypeList() {
        return daoType.getTypeList();
    }
}
