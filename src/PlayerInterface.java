interface PlayerInterface {
    public Coord takeTurn();
    public ShotResult receiveShot(Coord shot);
    public void recieveShotResult(ShotResult result);
    public Boolean validateTurn();
    public String getName();
}
