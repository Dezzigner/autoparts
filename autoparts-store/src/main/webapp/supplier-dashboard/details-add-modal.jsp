<div class="modal details_add_modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">.
            <form method="post" action="supplier-dashboard-details-add.action">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Add detail info</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Name: </label>
                        <input id="details_add_name" type="text" required class="form-control" name="detail.name">
                    </div>
                    <div class="form-group">
                        <label>Count in warehouse: </label>
                        <input id="details_add_count_in_warehouse" type="number" required class="form-control" name="detail.countInWarehouse">
                    </div>
                    <div class="form-group">
                        <label>Price: </label>
                        <input id="details_add_price" type="number" required class="form-control" name="detail.price">
                    </div>
                    <div class="form-group">
                        <label>Car: </label>
                        <select required class="form-control" name="detail.car.id">
                            <s:iterator value="carsList" var="car">
                                <option value="<s:property value="id"></s:property>">
                                    <s:property value="name"></s:property> . Year:
                                    <s:property value="releaseDate"></s:property>
                                </option>
                            </s:iterator>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Warehouse: </label>
                        <select required class="form-control" name="detail.warehouse.id">
                            <s:iterator value="warehousesList" var="warehouse">
                                <option value="<s:property value="id"></s:property>">
                                    <s:property value="name"></s:property> . Address:
                                    <s:property value="address"></s:property>
                                </option>
                            </s:iterator>
                        </select>
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

