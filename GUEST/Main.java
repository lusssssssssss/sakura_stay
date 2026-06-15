public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        AreYouMemberView areYouMemberView = new AreYouMemberView(model, model.connect());
        areYouMemberView.setVisible(true);
    }
}
