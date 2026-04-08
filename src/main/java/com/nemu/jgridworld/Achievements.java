package com.nemu.jgridworld;

public class Achievements {
    public final int WALL_HUGGER = 0,
            LEANED_NOTHING = 1,
            RUNNING_RAGGED = 2,
            IVE_GOT_TIME = 3,
            RISK_TAKER = 4,
            LIBRARIAN = 5,
            PAPER_PLEASE = 6,
            THOSE_PROBABLY_WERENT_IMPORTANT = 7,
            EDITOR_ARTISAN = 8,
            WHAT_DOES_THIS_BUTTON_DO = 9,
            LOST_IN_THOUGHT = 10,
            MAZE_MASTER = 11,
            SAVED_BY_THE_BELL = 12,
            NOTHING_BUT_LUCK = 13,
            MAXIMUM_OVERDRIVE = 14,
            ABORT_ABORT = 15,
            PRECISION_PLACEMENT = 16,
            FRIENDLY_FIRE = 17,
            TIGHT_FIT = 18,
            INDECISIVE = 19,
            SHUT_UP_ALREADY = 20,
            JUST_A_SCRATCH = 21,
            DID_I_DO_THAT = 22;
    final String[] ACHIEVEMENT_KEY_NAMES = {
            "achvWallHugger",
            "achvLeanedNothing",
            "achvRunningRagged",
            "achvIveGotTime",
            "achvRiskTaker",
            "achvLibrarian",
            "achvPaperPlease",
            "achvTPWI",
            "achvEditorArtisan",
            "achvWDTBD",
            "achvLostInThought",
            "achvMazeMaster",
            "achvSavedByTheBell",
            "achvNothingButLuck",
            "achvMaximumOverdrive",
            "achvAbortAbort",
            "achvPrecisionPlacement",
            "achvFriendlyFire",
            "achvTightFit",
            "achvIndecisive",
            "achvShutUpAlready",
            "achvJustAScratch",
            "achvDidIDoThat"
    };

    final String[] ACHIEVEMENT_NAMES = {
            "Wall Hugger",
            "Leaned Nothing",
            "Running Ragged",
            "I've Got Time",
            "Risk Taker",
            "Librarian",
            "Paper Please",
            "Those Probably Weren't Important",
            "Editor Artisan",
            "What Does This Button Do?",
            "Lost in Thought",
            "Maze Master",
            "Saved by the Bell",
            "Nothing but Luck",
            "Maximum Overdrive",
            "Abort! Abort!",
            "Precision Placement",
            "Friendly Fire",
            "Tight Fit",
            "Indecisive",
            "Shut Up Already!",
            "Just a Scratch!",
            "Did I Do That?"
    };

    final String[] ACHIEVEMENT_DESCRIPTIONS = {
            "Hit the wall at least 100 times in a single trial.",
            "Complete a simulation without any successful trials.",
            "Complete 1,000,000 trials in a single simulation.",
            "Start a simulation with 1,000,000 trials on the slowest speed.",
            "Have at least 50 reports in the report queue.",
            "Print at least 10 reports.",
            "Clear the report queue when it contains at least 20 reports.",
            "Create at least 20 custom GridWorlds with the editor.",
            "Click every button in the main window and the simulation options window.",
            "Complete a simulation with nothing but exploratory moves.",
            "Complete a simulation with an 80%+ success rate when there are 25+ walls in the GridWorld.",
            "Land on the prize on the very last move of a trial.",
            "Complete a simulation with 100% success rate when the learning rate is set to 0.",
            "Run a simulation with 1,000,000 trials and 999 moves.",
            "Abort at least 10 simulations.",
            "Place an oddly specific value in the GridWorld editor.",
            "Attempt to remove the agent in the GridWorld editor.",
            "Place an item in every cell of the GridWorld editor.",
            "Let the timeout of a yes/no prompt expire.",
            "Check every single \"Do not ask/show me this again\" checkbox in the program.",
            "Have the agent die at least 1,000 times."
    };

     
    public void check(int achievement) {

    }

    public void grant(int achievement) {
        if (Shared.preferenceManager.hasAchievement(ACHIEVEMENT_KEY_NAMES[achievement])) return;
        Shared.preferenceManager.grantAchievement(ACHIEVEMENT_KEY_NAMES[achievement]);
        Shared.messageFactory.setTitle("Achievement Unlocked!").setMessage("You have unlocked the " + ACHIEVEMENT_NAMES[achievement] + " achievement!<br><br>"
                + ACHIEVEMENT_DESCRIPTIONS[achievement]).setModal(true).create().setVisible(true);
    }

}
