public class Treadmill implements Let{
    private int length;

    Treadmill(int length) {
        this.length = length;
    }

    @Override
    public boolean pass(Participant participant) {
        boolean isPass = false;
        participant.run();
        if(participant.getMaxRun() >= length) {
            isPass = true;
        }
        System.out.printf("%s is%s pass the treadmill\n\r", participant.getName(), isPass ? "" : " not");
        return isPass;
    }
}
