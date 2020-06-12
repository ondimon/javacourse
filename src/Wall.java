public class Wall implements Let{
    private int height;

    Wall(int height) {
        this.height = height;
    }

    @Override
    public boolean pass(Participant participant) {
        boolean isPass = false;
        participant.jump();
        if(participant.getMaxJump() >= height) {
            isPass = true;
        }
        System.out.printf("%s is%s pass the wall\n\r", participant.getName(), isPass ? "" : " not");
        return isPass;
    }
}
