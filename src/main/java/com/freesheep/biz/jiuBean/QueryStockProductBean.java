package com.freesheep.biz.jiuBean;

import java.util.List;

public class QueryStockProductBean extends BaseBean {

    private List<CriteriaBean> criteriaList;

    public List<CriteriaBean> getCriteriaList() {
        return criteriaList;
    }

    public void setCriteriaList(List<CriteriaBean> criteriaList) {
        this.criteriaList = criteriaList;
    }
}
