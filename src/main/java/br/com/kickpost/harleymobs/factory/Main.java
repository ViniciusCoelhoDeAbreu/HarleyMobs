package br.com.kickpost.harleymobs.factory;

public abstract class Main implements AbstractMain
{
    public Main() {
        this.initialize();
        this.registerCommands();
        this.registerListeners();
    }
    
    @Override
    public abstract void initialize();
    
    @Override
    public abstract void registerCommands();
    
    @Override
    public abstract void registerListeners();
}
