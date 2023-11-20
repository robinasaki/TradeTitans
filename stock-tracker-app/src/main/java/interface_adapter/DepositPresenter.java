package interface_adapter;

import use_case.deposit.DepositOutputBoundary;
import use_case.deposit.DepositOutputData;

public class DepositPresenter implements DepositOutputBoundary {
    private final DepositViewModel depositViewModel;
    private ViewManagerModel viewManagerModel;

    public DepositPresenter(DepositViewModel depositViewModel,
                            ViewManagerModel viewManagerModel){
        this.depositViewModel = depositViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareSuccessView(DepositOutputData user) {
        DepositState depositState = depositViewModel.getState();
        // TODO: fix
        ViewManagerModel.setActiveView(depositViewModel.getViewName());
    }

    @Override
    public void prepareFailView(String error) {
        DepositState depositState = depositViewModel.getState();
        depositState.getNegativeDepositError();
        depositViewModel.firePropertyChanged();
    }
}
