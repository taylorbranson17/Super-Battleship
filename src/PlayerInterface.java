interface PlayerInterface {
    public Coord takeTurn();
    public ShotResult recieveShot();
    public void recieveShotResult();
    public Boolean validateTurn();
    public String getName();
}
