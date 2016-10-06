<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<% BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService(); %>
<section ng-controller="filesCtrl as vm">
    <div class="container wow fadeInUp">
        <h2>Add image to {{vm.obj.name}}</h2>
        <form action="<%= blobstoreService.createUploadUrl("/upload") %>" method="post" enctype="multipart/form-data">
            <input type="hidden" name="itemId" value="{{vm.obj.id}}">
            <%--<label class="btn btn-lg btn-danger">
                Browse&hellip; <input type="file" name="myFile" style="display: none;">
            </label>--%>

            <label class="file">
                <input type="file" name="myFile"  id="file">
                <span class="file-custom"></span>
            </label>


            <br><br>
            <input type="submit" value="Submit" class="btn btn-success">
            <a type="button" class="btn btn-secondary" href="/#items/"><i class="ion-android-arrow-back" role="button"></i>&nbsp;Back</a>
        </form>
    </div>
</section>