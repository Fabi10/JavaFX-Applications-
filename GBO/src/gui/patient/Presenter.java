package gui.patient;

public class Presenter {

    private View view;

    private Model model;

    private Dialog dialog;

    public Presenter() {

    }

    public void setView(View view) {
        this.view = view;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public View getView() {
        return view;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public Dialog getDialog() {
        dialog = new Dialog(this);
        return dialog;
    }

    public void addPatient(Patient p) {
        model.addPatient(p);
        view.updateTable(model.getData());
        System.out.println(model.getData().size());

    }

    public void deletePatient(Patient p) {
        model.deletePatient(p);
        view.updateTable(model.getData());

    }

}
