package me.zeroX150.atomic.feature.gui.hud.element;

import me.zeroX150.atomic.Atomic;
import me.zeroX150.atomic.helper.font.FontRenderers;
import me.zeroX150.atomic.helper.render.Renderer;
import me.zeroX150.atomic.helper.util.Utils;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public abstract class HudElement {
    static MatrixStack stack = new MatrixStack();
    double posX, posY;
    double width, height;
    boolean selected = false;
    String id;

    public HudElement(String id, double x, double y, double w, double h) {
        this.posX = x;
        this.posY = y;
        this.width = w;
        this.height = h;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public void renderOutline() {
        Renderer.R2D.lineScreenD(Utils.getCurrentRGB(), posX, posY, posX + width, posY);
        Renderer.R2D.lineScreenD(Utils.getCurrentRGB(), posX + width, posY, posX + width, posY + height);
        Renderer.R2D.lineScreenD(Utils.getCurrentRGB(), posX + width, posY + height, posX, posY + height);
        Renderer.R2D.lineScreenD(Utils.getCurrentRGB(), posX, posY + height, posX, posY);
        double rpoY = posY - FontRenderers.normal.getFontHeight();
        if (posY < FontRenderers.normal.getFontHeight()) { // too small to render text properly
            rpoY = posY + height;
        }
        FontRenderers.normal.drawString(Renderer.R3D.getEmptyMatrixStack(), id, posX, rpoY, 0xFFFFFF);
    }

    public abstract void renderIntern(MatrixStack stack);

    public void render() {
        stack.push();
        stack.translate(posX, posY, 0);
        renderIntern(stack);
        stack.pop();
    }

    public boolean mouseClicked(double x, double y) {
        if (inBounds(x, y)) {
            selected = true;
            return true;
        }
        return false;
    }

    public void mouseReleased() {
        this.selected = false;
    }

    public void mouseDragged(double deltaX, double deltaY) {
        if (selected) {
            this.posX += deltaX;
            this.posY += deltaY;
            this.posX = MathHelper.clamp(this.posX, 0, Atomic.client.getWindow().getScaledWidth() - this.width);
            this.posY = MathHelper.clamp(this.posY, 0, Atomic.client.getWindow().getScaledHeight() - this.height);
        }
    }

    boolean inBounds(double mx, double my) {
        return mx >= posX && mx < posX + width && my >= posY && my < posY + height;
    }

    public void fastTick() {
        this.posX = MathHelper.clamp(this.posX, 0, Atomic.client.getWindow().getScaledWidth() - this.width);
        this.posY = MathHelper.clamp(this.posY, 0, Atomic.client.getWindow().getScaledHeight() - this.height);
    }
}
