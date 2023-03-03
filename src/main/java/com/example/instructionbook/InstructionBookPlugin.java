package com.example.instructionbook;

import org.bukkit.plugin.java.JavaPlugin;

public class InstructionBookPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getCommand("instructions").setExecutor(new InstructionBookCommand(this));
    }
}
