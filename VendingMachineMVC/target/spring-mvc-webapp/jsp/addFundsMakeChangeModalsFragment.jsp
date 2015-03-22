<!--Add Funds Modal-->
<div class="modal fade" id="addFundsModal" tabindex="-1" role="dialog" aria-labelledby="addFundsModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span>X</span>
                </button>
                <h3 class="modal-title" id="addFundsModalLabel">Add Funds</h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="add-funds" class="col-md-4 control-label">
                            Enter amount of money to add to balance (example: 2.25):
                        </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="add-funds" placeholder="Amount to add">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <button type="submit" id="add-button" class="btn btn-primary" data-dismiss="modal">Confirm</button>
                            <button type="button" id="cancel-button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Make Change Modal -->
<div class="modal fade" id="makeChangeModal" tabindex="-1" role="dialog" aria-labelledby="makeChangeModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span>X</span>
                </button>
                <h3 class="modal-title" id="makeChangeModalLabel">Make Change</h3>
            </div>
            <div class="modal-body">
                <table class="table table-bordered">
                    <tr>
                        <th>Quarters:</th>
                        <td id="change-quarters"></td>
                    </tr>
                    <tr>
                        <th>Dimes:</th>
                        <td id="change-dimes"></td>
                    </tr>
                    <tr>
                        <th>Nickels:</th>
                        <td id="change-nickels"></td>
                    </tr>
                    <tr>
                        <th>Pennies:</th>
                        <td id="change-pennies"></td>
                    </tr>
                    <tr>
                        <th>Total Change Due:</th>
                        <td id="change-due"></td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button type="submit" id="change-button" class="btn btn-primary" data-dismiss="modal">Confirm</button>
                <button type="button" id="cancel-button" class="btn btn-default" data-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>
</div>