public class Main {

    public static void main(String[] args) {
        startCompetition();
    }

    public static void startCompetition() {
        Participant[] participants = new Participant[3];
        Let[] let = new Let[4];
        participants[0] = new Man("man 1", 100, 100);
        participants[1] = new Cat("cat 1", 15, 100);
        participants[2] = new Robot("robot 1", 50, 75);

        let[0] = new Treadmill(50);
        let[1] = new Wall(10);
        let[2] = new Treadmill(100);
        let[3] = new Wall(25);

        for (int i = 0; i < participants.length; i++) {
            for (int j = 0; j < let.length; j++) {
                if(! let[j].pass(participants[i])) {
                    break;
                };
            }
        }

    }
}
