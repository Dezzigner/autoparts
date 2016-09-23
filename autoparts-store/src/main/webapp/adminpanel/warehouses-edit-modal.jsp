<div class="modal warehouses_edit_modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">.
            <form method="post" action="adminpanel-warehouses-update.action">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Update warehouse info</h4>
                </div>
                <div class="modal-body">
                        <input id="warehouses_edit_id" name="warehouse.id" type="hidden">
                        <div class="form-group">
                            <label>Name: </label>
                            <input id="warehouses_edit_name" type="text" required class="form-control" name="warehouse.name">
                        </div>
                        <div class="form-group">
                            <label>Address: </label>
                            <input id="warehouses_edit_address" type="text" required class="form-control" name="warehouse.address">
                        </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary add-btn">Save changes</button>
                    </div>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
