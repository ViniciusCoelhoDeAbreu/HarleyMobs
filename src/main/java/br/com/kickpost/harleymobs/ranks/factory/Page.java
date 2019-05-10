package br.com.kickpost.harleymobs.ranks.factory;

public class Page
{
    private int page;
    private int currentIndex;
    
    public Page(final int page) {
        this.page = page;
        this.currentIndex = 0;
    }
    
    public int getPage() {
        return this.page;
    }
    
    public void setPage(final int page) {
        this.page = page;
    }
    
    public int getCurrentIndex() {
        return this.currentIndex;
    }
    
    public void setCurrentIndex(final int currentIndex) {
        this.currentIndex = currentIndex;
    }
}
