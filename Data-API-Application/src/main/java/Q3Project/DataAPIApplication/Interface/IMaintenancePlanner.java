package Q3Project.DataAPIApplication.Interface;

import Q3Project.DataAPIApplication.Model.Maintenance;

public interface IMaintenancePlanner extends IGeneric<Maintenance> {

    boolean CheckDoubleTimeDuration(Maintenance maintenance);
}
