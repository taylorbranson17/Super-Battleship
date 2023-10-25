interface PlayerInterface {
    public Coord takeTurn();
    public ShotResult receiveShot();
    public void recieveShotResult();
    public Boolean validateTurn();
    public String getName();
}
