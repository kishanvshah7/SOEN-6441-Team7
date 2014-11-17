package tdgame.controller;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import tdgame.model.GridCellModel;

public abstract class CretersControllerClass {
	

public abstract class Actor {
    /** The grid to which this actor belongs*/
    protected GridCellController grid = null;
    /** The cell in which this actor currently resides*/
    protected GridCellModel cell = null;
    /** The image of this actor*/
    protected BufferedImage actorImage;

    /**
     * Adds this actor to a given grid. An actor can exist in only one grid,
     * so this will result in the actor being removed from its current grid.
     * @param grid The grid to add this actor to.
     */

    public void addSelfToGrid (GridCellController grid) {
        if (this.grid != null)
      //      this.grid.removeActor(this);
        this.grid = grid;
     //   cell = grid.getPathEntrance();
     //   grid.addActor(this, cell.getRow(), cell.getColumn());
    }
    
    /**
     * Removes this actor from its current grid.
     */

    public void removeSelfFromGrid () {
        if (grid == null)
            return;
       // grid.removeActor(this);
    }

    /**
     * Renders this actor onto the given graphics object.
     * @param gx The graphics object to render onto.
     */

    public void render (Graphics2D gx) {
        if (actorImage != null)
            gx.drawImage(actorImage, 0, 0, null);
    }
    
    /**
     * Rendsers this cell with transparent images and a cell sheet
     * @param gx The graphics context
     */

    public void renderTransparent (Graphics2D gx, float alpha, Color color) {
        if (actorImage != null) {            
            gx.drawImage(actorImage, 0, 0, null);
            gx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            gx.fillRect(0, 0, actorImage.getWidth(), actorImage.getHeight());
        }
    }

    public BufferedImage getActorImage () {
        return actorImage;
    }

    /**
     * Called when the game cycle dictates that this actor may now 'perform'
     * some sort of action. The actor is responsible for figuring out whether
     * or not to act, and what it will do. This will be called by the game timer,
     * and player action should occur herein.
     */

    public abstract boolean doAction ();

    public GridCellModel getCell() {
        return cell;
    }   
}

	
	
}

