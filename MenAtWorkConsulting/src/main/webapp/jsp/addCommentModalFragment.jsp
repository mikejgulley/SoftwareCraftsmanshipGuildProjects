<!--Add Comment Modal-->
<div class="modal fade" id="createModal" tabindex="-1" role="dialog" aria-labelledby="createModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span>X</span>
                </button>
                <h3 class="modal-title" id="createModalLabel">Add Comment</h3>
            </div>
            <div class="modal-body">
                <!--<h3 id="post-id"></h3>-->
                <!--<h3>Add New Movie</h3>-->
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="add-title" class="col-md-2 control-label">
                            Comment:
                        </label>
                        <div class="col-md-10">
                            <textarea type="text" class="form-control" id="add-comment" placeholder="Comment" rows="10" style="max-width: 100%;"></textarea>
							<p hidden id="postId"></p>
                        </div>
                    </div>
<!--                    <div class="form-group">
                        <label for="add-release-date" class="col-md-4 control-label">
                            Release Date:
                        </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="add-release-date" placeholder="Release Date">
                        </div>
                    </div>-->
                    <div class="form-group">
                        <div class="col-md-offset-2 col-md-10">
                            <button type="submit" id="add-button" class="btn btn-primary" data-dismiss="modal">Add Comment</button>
                            <button type="button" id="cancel-button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>