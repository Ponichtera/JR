package gameSokoban.model;


public abstract class CollisionObject extends defaultGameObject {

    public CollisionObject(int x, int y) {
        super(x, y);
    }
    public boolean isCollision(defaultGameObject gameObject, Direction direction) {
        switch (direction){
            case UP:    if(getX() == gameObject.getX() && getY() + Model.FIELD_CELL_SIZE == gameObject.getY()) return true;
            case DOWN:  if(getX() == gameObject.getX() && getY() - Model.FIELD_CELL_SIZE == gameObject.getY()) return true;
            case LEFT:  if(getX() - Model.FIELD_CELL_SIZE == gameObject.getX() && getY() == gameObject.getY()) return true;
            case RIGHT: if(getX() + Model.FIELD_CELL_SIZE == gameObject.getX() && getY() == gameObject.getY()) return true;
            default: return false;
        }
    }
}
