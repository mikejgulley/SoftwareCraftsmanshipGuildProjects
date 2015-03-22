<!--Add Modal-->
<div class="modal fade" id="createModal" tabindex="-1" role="dialog" aria-labelledby="createModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span>X</span>
                </button>
                <h3 class="modal-title" id="createModalLabel">Create Movie</h3>
            </div>
            <div class="modal-body">
                <!--                <h3 id="movie-id"></h3>-->
<!--                <h3>Add New Movie</h3>-->
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="add-title" class="col-md-4 control-label">
                            Title:
                        </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="add-title" placeholder="Title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-release-date" class="col-md-4 control-label">
                            Release Date:
                        </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="add-release-date" placeholder="Release Date">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-mpaa-rating" class="col-md-4 control-label">
                            MPAA Rating:
                        </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="add-mpaa-rating" placeholder="MPAA Rating">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-director" class="col-md-4 control-label">
                            Director:
                        </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="add-director" placeholder="Director">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-studio" class="col-md-4 control-label">
                            Studio:
                        </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="add-studio" placeholder="Studio">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <button type="submit" id="add-button" class="btn btn-primary" data-dismiss="modal">Add Movie</button>
                            <button type="button" id="cancel-button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Details Modal -->
<div class="modal fade" id="detailsModal" tabindex="-1" role="dialog" aria-labelledby="detailsModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <!--                            <span class="sr-only">Close</span>-->
                    <span>X</span>
                </button>
                <h3 class="modal-title" id="detailsModalLabel">Movie Details</h3>
            </div>
            <div class="modal-body">
<!--                <h3 id="movie-id"></h3>-->
                <table class="table table-bordered">
                    <tr>
                        <th>Title:</th>
                        <td id="movie-title"></td>
                    </tr>
                    <tr>
                        <th>Release Date:</th>
                        <td id="movie-releaseDate"></td>
                    </tr>
                    <tr>
                        <th>MPAA Rating:</th>
                        <td id="movie-mpaaRating"></td>
                    </tr>
                    <tr>
                        <th>Director:</th>
                        <td id="movie-director"></td>
                    </tr>
                    <tr>
                        <th>Studio:</th>
                        <td id="movie-studio"></td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<!--Edit Modal-->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span>X</span>
                </button>
                <h3 class="modal-title" id="editModalLabel">Edit Movie</h3>
            </div>
            <div class="modal-body">
<!--                <h3 id="movie-id"></h3>-->
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="edit-title" class="col-md-4 control-label">Title</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="edit-title" placeholder="Title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-release-date" class="col-md-4 control-label">Release Date</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="edit-release-date" placeholder="Release Date">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-mpaa-rating" class="col-md-4 control-label">MPAA Rating</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="edit-mpaa-rating" placeholder="MPAA Rating">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-director" class="col-md-4 control-label">Director</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="edit-director" placeholder="Director">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-studio" class="col-md-4 control-label">Studio</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="edit-studio" placeholder="Studio">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <button type="submit" id="edit-button" class="btn btn-default" data-dismiss="modal">Edit Movie</button>
                            <button type="button" id="cancel-button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                            <input type="hidden" id="edit-movie-id">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>