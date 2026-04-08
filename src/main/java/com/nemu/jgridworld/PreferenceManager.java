package com.nemu.jgridworld;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Optional;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.intellijthemes.*;
import com.jthemedetecor.OsThemeDetector;

import javax.swing.*;

import static com.nemu.jgridworld.Shared.*;

public class PreferenceManager {
    final OsThemeDetector osThemeDetector = OsThemeDetector.getDetector();
    private long startupTime = System.currentTimeMillis();
    Preferences preferences;
    Preferences promptFlags;
    Preferences statistics;
    Preferences achievements;
  
    Hashtable<String, String> defaultPreferences = new Hashtable<>();
  
    HashMap<String, String> defaultPromptFlags = new HashMap<>();
  
    HashMap<String, String> defaultStatistics = new HashMap<>();
  
    HashMap<String, String> defaultAchievements = new HashMap<>();
  
    HashMap<String, String> accentColors = new HashMap<>() {
        {
            put("lightBlue", "rgb(78, 157, 231)");
            put("darkBlue", "rgb(0, 122, 255)");
            put("purple", "rgb(191, 90, 242)");
            put("red", "rgb(255, 59, 48)");
            put("orange", "rgb(255, 149, 0)");
            put("yellow", "rgb(255, 204, 0)");
            put("green", "rgb(40, 205, 65)");
        }
    };

    PreferenceManager() {
        preferences = Preferences.userRoot().node("JGridWorldPreferences");
        promptFlags = Preferences.userRoot().node("JGridWorldPromptFlags");
        statistics = Preferences.userRoot().node("JGridWorldStatistics");
        achievements = Preferences.userRoot().node("JGridWorldAchievements");
      
        defaultPreferences.put("hasRunAtLeastOnce", "true");
        defaultPreferences.put("uiPreferredTheme", "IntelliJ");
        defaultPreferences.put("uiPreferredLightTheme", "IntelliJ");
        defaultPreferences.put("uiPreferredDarkTheme", "Darcula");
        defaultPreferences.put("uiSyncThemeWithOS", "false");
        defaultPreferences.put("uiSystemUsesDarkTheme", String.valueOf(osThemeDetector.isDark()));
        defaultPreferences.put("uiPreferredAccentColor", "theme");
        defaultPreferences.put("uiPrefersRoundedComponents", String.valueOf(!System.getProperty("os.name").toLowerCase().contains("windows 10")));
        defaultPreferences.put("uiPrefersCircularComponents", String.valueOf(System.getProperty("os.name").toLowerCase().contains("mac")));
        defaultPreferences.put("uiAlwaysUnderlineAccessKeys", "false");
        defaultPreferences.put("uiRememberWindowSizeLocation", "false");
        defaultPreferences.put("uiWindowWasMaximized", "false");
        defaultPreferences.put("uiWindowWidth", "1360");
        defaultPreferences.put("uiWindowHeight", "750");
        defaultPreferences.put("uiWindowX", "10");
        defaultPreferences.put("uiWindowY", "10");
        defaultPreferences.put("uiDefaultStartupTab", "0");
        defaultPreferences.put("uiMaxLogSize", "9999");
        defaultPreferences.put("uiAutoScrollLog", "true");
        defaultPreferences.put("funStuffEnableWindowEffects", "false");
        defaultPreferences.put("funStuffEnableAchievements", "false");
        defaultPreferences.put("funStuffEnableStatusBarQuotes", "true");
        defaultPreferences.put("fileAutoLoadLastConfiguration", "false");
        defaultPreferences.put("fileAutoLoadLastConfigurationPath", "");
        defaultPreferences.put("fileDefaultConfigDirectory", createDefaultConfigDirectory());
        defaultPreferences.put("fileIgnoreBadChecksums", "false");
      

      
        defaultPromptFlags.put("noAskCloseProgram", "false");
        defaultPromptFlags.put("noAskLoadConfiguration", "false");
        defaultPromptFlags.put("noAskOverwriteConfiguration", "false");
        defaultPromptFlags.put("rememberAskViewSimulation", "");
        defaultPromptFlags.put("rememberAskUsePreviousValues", "");
        defaultPromptFlags.put("noAskClearReports", "false");
        defaultPromptFlags.put("noAskVoidReport", "false");
        defaultPromptFlags.put("noAskPrintReport", "false");
        defaultPromptFlags.put("noShowNoPrevReportsWarning", "false");
        defaultPromptFlags.put("noShowNoNextReportsWarning", "false");
      

      
        defaultStatistics.put("statsTotalTimesRun", "1");
        defaultStatistics.put("statsTotalUptime", "0");
        defaultStatistics.put("statsTotalNumSimulations", "0");
        defaultStatistics.put("statsTotalNumCompletedSimulations", "0");
        defaultStatistics.put("statsTotalNumAbortedSimulations", "0");
        defaultStatistics.put("statsTotalNumTrials", "0");
        defaultStatistics.put("statsTotalNumSuccesses", "0");
        defaultStatistics.put("statsTotalNumMoves", "0");
        defaultStatistics.put("statsTotalNumExploratoryMoves", "0");
        defaultStatistics.put("statsTotalNumExploitativeMoves", "0");
        defaultStatistics.put("statsTotalNumAgentDeaths", "0");
        defaultStatistics.put("statsTotalNumReportsAtOnce", "0");
        defaultStatistics.put("statsTotalNumReportsVoided", "0");
        defaultStatistics.put("statsTotalNumReportsPrinted", "0");
        defaultStatistics.put("statsTotalNumGridWorldsCreated", "0");
        defaultStatistics.put("statsTotalNumConfigsSaved", "0");
        defaultStatistics.put("statsTotalNumConfigsLoaded", "0");
      

      
        defaultAchievements.put("achvWallHugger", "false");
        defaultAchievements.put("achvLearnedNothing", "false");
        defaultAchievements.put("achvRunningRagged", "false");
        defaultAchievements.put("achvIveGotTime", "false");
        defaultAchievements.put("achvRiskTaker", "false");
        defaultAchievements.put("achvLibrarian", "false");
        defaultAchievements.put("achvPaperPlease", "false");
        defaultAchievements.put("achvTPWI", "false");
        defaultAchievements.put("achvEditorArtisan", "false");
        defaultAchievements.put("achvWDTBD", "false");
        defaultAchievements.put("achvLostInThought", "false");
        defaultAchievements.put("achvMazeMaster", "false");
        defaultAchievements.put("achvSavedByTheBell", "false");
        defaultAchievements.put("achvNothingButLuck", "false");
        defaultAchievements.put("achvMaximumOverdrive", "false");
        defaultAchievements.put("achvAbortAbort", "false");
        defaultAchievements.put("achvPrecisionPlacement", "false");
        defaultAchievements.put("achvFriendlyFire", "false");
        defaultAchievements.put("archTightFit", "false");
        defaultAchievements.put("achvIndecisive", "false");
        defaultAchievements.put("achvShutUpAlready", "false");
        defaultAchievements.put("achvJustAScratch", "false");
        defaultAchievements.put("achvDidIDoThat", "false");
      


      
        if ("false".equals(preferences.get("hasRunAtLeastOnce", "false"))) {
            log("Could not locate preferences file. Something or somebody made it disappear. Creating a new one.");
            this.buildInitialPreferenceStore();
        }

        preferences.put("uiSystemUsesDarkTheme", String.valueOf(osThemeDetector.isDark()));
        osThemeDetector.registerListener(isDark -> {
            Shared.log("System theme changed to " + (isDark ? "dark" : "light"), "info");
            if (getPreferenceAsBoolean("uiSyncThemeWithOS")) {
                SwingUtilities.invokeLater(() -> {
                    if (isDark) {
                        setPreference("uiSystemUsesDarkTheme", "true");
                    }
                    else {
                        setPreference("uiSystemUsesDarkTheme", "false");
                    }
                    syncThemeWithOS();
                    FlatLaf.updateUI();
                });
            }
        });
    }

    void setPromptFlag(String key) {
        setPromptFlag(key, true);
    }

    void setPromptFlag(String key, boolean value) {
        try {
            promptFlags.put(key, String.valueOf(value));
          
            Shared.achievements.check(Shared.achievements.SHUT_UP_ALREADY);
        }
        catch (Exception e) {
            log("Error setting prompt flag: " + e.getMessage(), "error");
        }
    }

    boolean getPromptFlag(String key) {
        return "true".equalsIgnoreCase(promptFlags.get(key, "false"));
    }

    Optional<Boolean> getPromptPreferredAction(String key) {
        if ("".equals(promptFlags.get(key, ""))) return Optional.empty();
        return Optional.of("true".equalsIgnoreCase(promptFlags.get(key, "false")));
    }

    void setStatistic(String key, int value) {
        try {
            statistics.put(key, String.valueOf(value));
        }
        catch (Exception e) {
            log("Error setting statistic: " + e.getMessage(), "error");
        }
    }

    void setStatistic(String key, double value) {
        try {
            statistics.put(key, String.valueOf(value));
        }
        catch (Exception e) {
            log("Error setting statistic: " + e.getMessage(), "error");
        }
    }

    void setStatistic(String key, long value) {
        try {
            statistics.put(key, String.valueOf(value));
        }
        catch (Exception e) {
            log("Error setting statistic: " + e.getMessage(), "error");
        }
    }

    int getStatisticAsInt(String key) {
        String value = statistics.get(key, "0");
        try {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException e) {
            log("Error parsing statistic as int: " + key, "error");
            return 0;
        }
    }

    double getStatisticAsDouble(String key) {
        String value = statistics.get(key, "0.0");
        try {
            return Double.parseDouble(value);
        }
        catch (NumberFormatException e) {
            log("Error parsing statistic as double: " + key, "error");
            return 0.0;
        }
    }

    long getStatisticAsLong(String key) {
        String value = statistics.get(key, "0");
        try {
            return Long.parseLong(value);
        }
        catch (NumberFormatException e) {
            log("Error parsing statistic as long: " + key, "error");
            return 0;
        }
    }

    void grantAchievement(String key) {
        try {
            achievements.put(key, "true");
        }
        catch (Exception e) {
            log("Error granting achievement: " + e.getMessage(), "error");
        }
    }

    boolean hasAchievement(String key) {
        return "true".equalsIgnoreCase(achievements.get(key, "false"));
    }

    boolean setPreference(String key, String value) {
        try {
            preferences.put(key, value);
            return true;
        }
        catch (Exception e) {
            System.out.println("Error setting preference: " + e.getMessage());
            return false;
        }
    }

    boolean setPreference(String key, int value) {
        try {
            preferences.put(key, String.valueOf(value));
            return true;
        }
        catch (Exception e) {
            log("Error setting preference: " + e.getMessage(), "error");
            return false;
        }
    }

    boolean setPreference(String key, double value) {
        try {
            preferences.put(key, String.valueOf(value));
            return true;
        }
        catch (Exception e) {
            log("Error setting preference: " + e.getMessage(), "error");
            return false;
        }
    }

    boolean setPreference(String key, boolean value) {
        try {
            preferences.put(key, String.valueOf(value));
            return true;
        }
        catch (Exception e) {
            log("Error setting preference: " + e.getMessage(), "error");
            return false;
        }
    }

    String getPreference(String key) {
        return preferences.get(key, "false");
    }

    int getPreferenceAsInt(String key) {
        String value = preferences.get(key, "0");
        try {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException e) {
            log("Error parsing preference as int: " + key, "error");
            return 0;
        }
    }

    double getPreferenceAsDouble(String key) {
        String value = preferences.get(key, "0.0");
        try {
            return Double.parseDouble(value);
        }
        catch (NumberFormatException e) {
            log("Error parsing preference as double: " + key, "error");
            return 0.0;
        }
    }

    boolean getPreferenceAsBoolean(String key) {
        String value = preferences.get(key, "false");
        return "true".equalsIgnoreCase(value);
    }

    String getDefaultPreference(String key) {
        return defaultPreferences.get(key);
    }

    boolean removePreference(String key) {
        try {
            preferences.remove(key);
            return true;
        }
        catch (Exception e) {
            log("Error removing preference: " + key, "error");
            return false;
        }
    }

    boolean clearPreferences() {
        try {
            preferences.clear();
            return true;
        }
        catch (Exception e) {
            log("Error clearing preferences: " + e.getMessage(), "error");
            return false;
        }
    }

    void resetPreferences() {
        try {
            preferences.clear();
            for (String key : defaultPreferences.keySet()) {
                preferences.put(key, defaultPreferences.get(key));
            }
        }
        catch (Exception e) {
            log("Error resetting preferences: " + e.getMessage(), "error");
        }
    }

    void resetPromptFlags() {
        try {
            promptFlags.clear();
            for (String key : defaultPromptFlags.keySet()) {
                promptFlags.put(key, defaultPromptFlags.get(key));
            }
        }
        catch (Exception e) {
            log("Error resetting prompt flags: " + e.getMessage(), "error");
        }
    }

    void resetStatistics() {
        try {
            statistics.clear();
            for (String key : defaultStatistics.keySet()) {
                statistics.put(key, defaultStatistics.get(key));
            }
        }
        catch (Exception e) {
            log("Error resetting statistics: " + e.getMessage(), "error");
        }
    }

    void resetAchievements() {
        try {
            achievements.clear();
            for (String key : defaultAchievements.keySet()) {
                achievements.put(key, defaultAchievements.get(key));
            }
        }
        catch (Exception e) {
            log("Error resetting achievements: " + e.getMessage(), "error");
        }
    }

    void buildInitialPreferenceStore() {
        try {
            log("Building initial preference store...", "info");
            resetPreferences();
            resetPromptFlags();
            resetStatistics();
            resetAchievements();
        }
        catch (Exception e) {
            log("Error restoring default preferences: " + e.getMessage(), "error");
        }
    }

    public void onStartup() {
        setStatistic("statsTotalTimesRun", getStatisticAsInt("statsTotalTimesRun") + 1);
        if (getPreferenceAsBoolean("fileAutoLoadLastConfiguration")) {
            String lastConfigPath = getPreference("fileAutoLoadLastConfigurationPath");
            if (!lastConfigPath.isEmpty()) {
                try {
                    Shared.lastFile = new File(lastConfigPath);
                    Shared.stm.loadConfiguration(Shared.lastFile, true);
                    Shared.log("Loaded configuration from " + lastConfigPath, "info");
                    setPreference("fileAutoLoadLastConfigurationPath", "");
                }
                catch (IOException e) {
                    log("Error loading last configuration: " + e.getMessage(), "error");
                }
            }
        }
        applyPreferences();
        if (getPreferenceAsBoolean("funStuffEnableStatusBarQuotes")) {
            Shared.setRandomStatusBarQuote();
        }
        Shared.mainWindow.pack();
        if (getPreferenceAsBoolean("uiRememberWindowSizeLocation")) {
            if (getPreferenceAsBoolean("uiWindowWasMaximized")) {
                Shared.mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
            else {
                Shared.mainWindow.setExtendedState(JFrame.NORMAL);
                Shared.mainWindow.setSize(getPreferenceAsInt("uiWindowWidth"), getPreferenceAsInt("uiWindowHeight"));
                Shared.mainWindow.setLocation(getPreferenceAsInt("uiWindowX"), getPreferenceAsInt("uiWindowY"));
            }
        }
    }

    public void onExit() {
        if (getPreferenceAsBoolean("uiRememberWindowSizeLocation")) {
            setPreference("uiWindowWidth", Shared.mainWindow.getWidth());
            setPreference("uiWindowHeight", Shared.mainWindow.getHeight());
            setPreference("uiWindowX", Shared.mainWindow.getX());
            setPreference("uiWindowY", Shared.mainWindow.getY());
            setPreference("uiWindowWasMaximized", Shared.mainWindow.getExtendedState() == JFrame.MAXIMIZED_BOTH);
        }
        if (getPreferenceAsBoolean("fileAutoLoadLastConfiguration") && Shared.lastFile != null) {
            setPreference("fileAutoLoadLastConfigurationPath", Shared.lastFile.getAbsolutePath());
        }
        setStatistic("statsTotalUptime", System.currentTimeMillis() - startupTime);
        try {
            preferences.flush();
            promptFlags.flush();
            statistics.flush();
            achievements.flush();
        }
        catch (BackingStoreException e) {
            log("Error saving preferences: " + e.getMessage(), "error");
        }
    }

    public void applyPreferences() {
        log("Reading preferences...", "info");
      
        int arc = getPreferenceAsBoolean("uiPrefersCircularComponents") && getPreferenceAsBoolean("uiPrefersRoundedComponents") ? 999 : getPreferenceAsBoolean("uiPrefersRoundedComponents") ? 6 : 0;
        UIManager.put("Button.arc", arc);
        UIManager.put("Component.arc", arc);
        UIManager.put("CheckBox.arc", arc);
        UIManager.put("ProgressBar.arc", arc);
        UIManager.put( "TextComponent.arc", arc);
        UIManager.put("TabbedPane.cardTabArc", arc);
      
        UIManager.put( "Component.hideMnemonics", !getPreferenceAsBoolean("uiAlwaysUnderlineAccessKeys"));
      
        UIManager.put("TabbedPane.tabType", "card");
        UIManager.put("TabbedPane.tabWidthMode", "equal");
      
        if (!getPreference("uiPreferredAccentColor").equals("theme")) {
            FlatLaf.setGlobalExtraDefaults(Collections.singletonMap("@accentColor", accentColors.get(getPreference("uiPreferredAccentColor"))));
        }
        else {
            FlatLaf.setGlobalExtraDefaults(Collections.singletonMap("@accentColor", ""));
        }
      
        setPreference("uiSystemUsesDarkTheme", String.valueOf(osThemeDetector.isDark()));
      
        if (getPreferenceAsBoolean("uiSyncThemeWithOS")) {
            syncThemeWithOS();
        }
        else {
            setThemeByName(getPreference("uiPreferredTheme"));
        }
        FlatLaf.updateUI();
        repackAllWindows();
        Shared.setAutoScrollLogCheckBoxMenuItem(getPreferenceAsBoolean("uiAutoScrollLog"));
        Shared.mainWindow.updateLogColors();
    }

    private void syncThemeWithOS() {
        if (getPreferenceAsBoolean("uiSystemUsesDarkTheme")) {
            setThemeByName(getPreference("uiPreferredDarkTheme"));
        }
        else {
            setThemeByName(getPreference("uiPreferredLightTheme"));
        }
    }

    public void repackAllWindows() {
        for (Window window : Window.getWindows()) {
            if (window.isVisible()) {
                Dimension oldSize = window.getSize();
                boolean wasMaximized = false;
                if (window instanceof JFrame) {
                    if (((JFrame) window).getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                        wasMaximized = true;
                    }
                }
                window.pack();
                if (wasMaximized) {
                    ((JFrame) window).setExtendedState(JFrame.MAXIMIZED_BOTH);
                }
                else {
                    window.setSize(oldSize);
                }
            }
        }
    }

    public void setThemeByName(String themeName) {
        switch (themeName) {
          
            case "IntelliJ" -> FlatLaf.setup(new FlatIntelliJLaf());
            case "Arc" -> FlatLaf.setup(new FlatArcIJTheme());
            case "Arc - Orange" -> FlatLaf.setup(new FlatArcOrangeIJTheme());
            case "Cyan Light" -> FlatLaf.setup(new FlatCyanLightIJTheme());
            case "Gray" -> FlatLaf.setup(new FlatGrayIJTheme());
            case "Light Flat" -> FlatLaf.setup(new FlatLightFlatIJTheme());
            case "Solarized Light" -> FlatLaf.setup(new FlatSolarizedLightIJTheme());

          
            case "Arc Dark" -> FlatLaf.setup(new FlatArcDarkIJTheme());
            case "Arc Dark - Orange" -> FlatLaf.setup(new FlatArcDarkOrangeIJTheme());
            case "Carbon" -> FlatLaf.setup(new FlatCarbonIJTheme());
            case "Cobalt 2" -> FlatLaf.setup(new FlatCobalt2IJTheme());
            case "Dark Flat" -> FlatLaf.setup(new FlatDarkFlatIJTheme());
            case "Darcula" -> FlatLaf.setup(new FlatDarculaLaf());
            case "Gradianto Dark Fuschia" -> FlatLaf.setup(new FlatGradiantoDarkFuchsiaIJTheme());
            case "Gradianto Deep Ocean" -> FlatLaf.setup(new FlatGradiantoDeepOceanIJTheme());
            case "Gradianto Midnight Blue" -> FlatLaf.setup(new FlatGradiantoMidnightBlueIJTheme());
            case "Gradianto Nature Green" -> FlatLaf.setup(new FlatGradiantoNatureGreenIJTheme());
            case "Gruvbox Dark Hard" -> FlatLaf.setup(new FlatGruvboxDarkHardIJTheme());
            case "Hiberbee Dark" -> FlatLaf.setup(new FlatHiberbeeDarkIJTheme());
            case "High Contrast" -> FlatLaf.setup(new FlatHighContrastIJTheme());
            case "Material Design Dark" -> FlatLaf.setup(new FlatMaterialDesignDarkIJTheme());
            case "Monocai" -> FlatLaf.setup(new FlatMonocaiIJTheme());
            case "Monokai Pro" -> FlatLaf.setup(new FlatMonokaiProIJTheme());
            case "Nord" -> FlatLaf.setup(new FlatNordIJTheme());
            case "One Dark" -> FlatLaf.setup(new FlatOneDarkIJTheme());
            case "Solarized Dark" -> FlatLaf.setup(new FlatSolarizedDarkIJTheme());
            case "Spacegray" -> FlatLaf.setup(new FlatSpacegrayIJTheme());
            case "Vuesion" -> FlatLaf.setup(new FlatVuesionIJTheme());
            case "Xcode-Dark" -> FlatLaf.setup(new FlatXcodeDarkIJTheme());

            default -> {
                log("Unknown theme: " + themeName + ". Using default theme.", "error");
                FlatLaf.setup(new FlatLightLaf());
            }
        }
    }

    public String[] getAvailableThemes() {
        return getAvailableThemes("");
    }

    public String[] getAvailableThemes(String filter) {
        String[] themes = {
                "IntelliJ", "Arc", "Arc - Orange", "Cyan Light", "Gray", "Light Flat", "Solarized Light",
                "Arc Dark", "Arc Dark - Orange", "Carbon", "Cobalt 2", "Dark Flat", "Darcula",
                "Gradianto Dark Fuschia", "Gradianto Deep Ocean", "Gradianto Midnight Blue",
                "Gradianto Nature Green", "Gruvbox Dark Hard", "Hiberbee Dark", "High Contrast",
                "Material Design Dark", "Monocai", "Monokai Pro", "Nord", "One Dark",
                "Solarized Dark", "Spacegray", "Vuesion", "Xcode-Dark"
        };
        if ("light".equals(filter)) {
          
            String[] lightThemes = new String[7];
            System.arraycopy(themes, 0, lightThemes, 0, 7);
            return lightThemes;
        }
        else if ("dark".equals(filter)) {
          
            String[] darkThemes = new String[themes.length - 7];
            System.arraycopy(themes, 7, darkThemes, 0, themes.length - 7);
            return darkThemes;
        }
        return themes;
    }

    public String createDefaultConfigDirectory() {
        String defaultConfigDirectory = System.getProperty("user.home") + FileSystems.getDefault().getSeparator() + "JGridWorld" + FileSystems.getDefault().getSeparator() + "configurations";
        try {
            File configDir = new File(defaultConfigDirectory);
            if (!configDir.exists()) {
                Files.createDirectories(configDir.toPath());
                log("Created default configuration directory: " + defaultConfigDirectory, "info");
            }
            return configDir.getAbsolutePath();
        }
        catch (IOException e) {
            log("Error creating default configuration directory: " + e.getMessage(), "error");
            return ".";
        }
    }
}
