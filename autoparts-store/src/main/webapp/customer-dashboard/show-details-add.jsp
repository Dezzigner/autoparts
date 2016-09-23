<div class="modal details_add_modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">.
            <form method="post" action="show-details-add.action">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Add detail in order</h4>
                </div>
                <div class="modal-body">
                    <input id="details_add_id" name="detailId" type="hidden">
                    <div class="form-group">
                        <label>Name: </label>
                        <input id="details_add_name" type="text" required class="form-control" name="nameDetail" disabled>
                    </div>
                    <div class="form-group">
                        <label>For car: </label>
                        <input id="details_add_car_name" type="text" required class="form-control" name="carDetail" disabled>
                    </div>
                    <div class="form-group">
                        <label>Car release year: </label>
                        <input id="details_add_car_release_year" type="text" required class="form-control" name="nameDetail" disabled>
                    </div>
                    <div class="form-group">
                        <label>Warehouse name: </label>
                        <input id="details_add_warehouse_name" type="text" required class="form-control" name="nameDetail" disabled>
                    </div>
                    <div class="form-group">
                        <label>Warehouse address: </label>
                        <input id="details_add_warehouse_address" type="text" required class="form-control" name="nameDetail" disabled>
                    </div>
                    <div class="form-group">
                        <label>Company name: </label>
                        <input id="details_add_company_name" type="text" required class="form-control" name="nameDetail" disabled>
                    </div>
                    <div class="form-group">
                        <label>Count: </label>
                        <input id="details_add_count" type="number" required class="form-control" name="count" value="1">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary add-btn">Order</button>
                    </div>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


